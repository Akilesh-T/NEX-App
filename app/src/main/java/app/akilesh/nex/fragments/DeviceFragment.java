package app.akilesh.nex.fragments;

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
import app.akilesh.nex.R;

import static android.content.ContentValues.TAG;


public class DeviceFragment extends Fragment {

    public static DeviceFragment newInstance() {
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

        String buildVer = "Unknown";

        try {
            Process process = Runtime.getRuntime().exec("su");
            InputStream in = process.getInputStream();
            OutputStream out = process.getOutputStream();
            String cmd = "[ -r /data/misc/box/report/PREV_VER ] && cat /data/misc/box/report/PREV_VER";
            out.write(cmd.getBytes());
            out.flush();
            out.close();
            process.waitFor();

            if (process.exitValue() != 0) {
                Log.e(TAG,"Failed to obtain root");
            }
            else {
                int ch;
                StringBuilder sb = new StringBuilder();
                while((ch = in.read()) != -1)
                    sb.append((char)ch);
                buildVer = sb.toString();
            }

            buildVer = buildVer.trim();

        } catch (IOException e) {
                Log.e(TAG, "IOException, " + e.getMessage());

        } catch (InterruptedException e) {
                Log.e(TAG, "InterruptedException, " + e.getMessage());
        }

        TextView build = view.findViewById(R.id.buildVersion);
        if(!buildVer.isEmpty()){
        build.setText(String.format("%s", buildVer));
        }
        else  build.setText(String.format("%s", "Not found"));
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnFragmentInteractionListener {
    }

}
