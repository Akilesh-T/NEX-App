package app.akilesh.nex.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import app.akilesh.nex.BuildConfig;
import app.akilesh.nex.R;

public class AboutFragment extends Fragment {

   public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.about_fragment, container, false);
        TextView ver = view.findViewById(R.id.version);
        String versionName = BuildConfig.VERSION_NAME;
        ver.setText(String.format("%s", versionName));
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
    }
}