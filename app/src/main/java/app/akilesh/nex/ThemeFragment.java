package app.akilesh.nex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class ThemeFragment extends Fragment {


    private OnFragmentInteractionListener listener;
    static ThemeFragment newInstance() {
        return new ThemeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.theme_fragment, container, false);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        Button btnNightMode = view.findViewById(R.id.btnNightMode);
        Button btnDayMode = view.findViewById(R.id.btnDayMode);


        btnNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Default Night Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("ThemePrefs",2);
                editor.apply();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("app.akilesh.nex", "app.akilesh.nex.SplashActivity");
                startActivity(intent);
            }
        });

        btnDayMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Default Day Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("ThemePrefs",1);
                editor.apply();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("app.akilesh.nex", "app.akilesh.nex.SplashActivity");
                startActivity(intent);
            }
        });
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
