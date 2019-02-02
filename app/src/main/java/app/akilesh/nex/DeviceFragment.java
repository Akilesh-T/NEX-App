package app.akilesh.nex;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static android.content.ContentValues.TAG;


public class DeviceFragment extends Fragment {
    private OnFragmentInteractionListener listener;

    static DeviceFragment newInstance() {
        return new DeviceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.device_fragment, container, false);

        String brand = Build.BRAND;
        TextView deviceBrand = view.findViewById(R.id.brand);
        deviceBrand.setText(String.format("%s", brand));

        String model = Build.MODEL;
        TextView deviceModel = view.findViewById(R.id.model);
        deviceModel.setText(String.format("%s", model));

        String code = Build.DEVICE;
        TextView deviceCodeName = view.findViewById(R.id.codeName);
        deviceCodeName.setText(String.format("%s", code));

        String buildVer = null;
        try {
        Process process = Runtime.getRuntime().exec("su");
        InputStream in = process.getInputStream();
        OutputStream out = process.getOutputStream();
        String cmd = "cat /data/misc/box/report/PREV_VER";
        out.write(cmd.getBytes());
        out.flush();
        out.close();
        byte[] buffer = new byte[1024];
        int length = in.read(buffer);
        buildVer = new String(buffer, 0, length);
        process.waitFor();
        } catch (IOException e) {
            Log.e(TAG, "IOException, " + e.getMessage());
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException, " + e.getMessage());
        }

        TextView build = view.findViewById(R.id.buildVersion);
        build.setText(String.format("%s", buildVer));

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    interface OnFragmentInteractionListener {
    }

}
