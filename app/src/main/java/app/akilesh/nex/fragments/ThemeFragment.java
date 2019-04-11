package app.akilesh.nex.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import app.akilesh.nex.R;

public class ThemeFragment extends Fragment {
    private String currentNightMode[] = {"Use system default", "Light", "Dark", "Set by Battery saver"};
    public static ThemeFragment newInstance() {
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
        TextView textView = view.findViewById(R.id.current_night_mode);
        Button btnNightMode = view.findViewById(R.id.btnNightMode);
        Button btnDayMode = view.findViewById(R.id.btnDayMode);
        Button btnAutoBatteryMode = view.findViewById(R.id.btnAutoBatteryMode);
        Button btnFollowSystem = view.findViewById(R.id.btnFollowSystem);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == 1) textView.setText(String.format("%s", currentNightMode[1]));
        if(nightMode == 2) textView.setText(String.format("%s", currentNightMode[2]));
        if(nightMode == 3) textView.setText(String.format("%s", currentNightMode[3]));
        if(nightMode == -1) textView.setText(String.format("%s", currentNightMode[0]));

        btnAutoBatteryMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("ThemePrefs",3);
                editor.apply();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("app.akilesh.nex", "app.akilesh.nex.SplashActivity");
                startActivity(intent);
            }
        });

        btnFollowSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("ThemePrefs",-1);
                editor.apply();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("app.akilesh.nex", "app.akilesh.nex.SplashActivity");
                startActivity(intent);
            }
        });

        btnNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {

    }

}
