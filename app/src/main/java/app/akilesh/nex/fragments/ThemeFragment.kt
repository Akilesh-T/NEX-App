package app.akilesh.nex.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import app.akilesh.nex.R

class ThemeFragment : Fragment() {
    private val currentNightMode = arrayOf("Use system default", "Light", "Dark", "Set by Battery saver")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.theme_fragment, container, false)
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity as Context?)
        val textView = view.findViewById<TextView>(R.id.current_night_mode)
        val btnNightMode = view.findViewById<Button>(R.id.btnNightMode)
        val btnDayMode = view.findViewById<Button>(R.id.btnDayMode)
        val btnAutoBatteryMode = view.findViewById<Button>(R.id.btnAutoBatteryMode)
        val btnFollowSystem = view.findViewById<Button>(R.id.btnFollowSystem)

        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == 1) textView.text = String.format("%s", currentNightMode[1])
        if (nightMode == 2) textView.text = String.format("%s", currentNightMode[2])
        if (nightMode == 3) textView.text = String.format("%s", currentNightMode[3])
        if (nightMode == -1) textView.text = String.format("%s", currentNightMode[0])

        btnAutoBatteryMode.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            val editor = sharedPref.edit()
            editor.putInt("ThemePrefs", 3)
            editor.apply()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.setClassName("app.akilesh.nex", "app.akilesh.nex.SplashActivity")
            startActivity(intent)
        }

        btnFollowSystem.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            val editor = sharedPref.edit()
            editor.putInt("ThemePrefs", -1)
            editor.apply()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.setClassName("app.akilesh.nex", "app.akilesh.nex.SplashActivity")
            startActivity(intent)
        }

        btnNightMode.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            val editor = sharedPref.edit()
            editor.putInt("ThemePrefs", 2)
            editor.apply()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.setClassName("app.akilesh.nex", "app.akilesh.nex.SplashActivity")
            startActivity(intent)
        }

        btnDayMode.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            val editor = sharedPref.edit()
            editor.putInt("ThemePrefs", 1)
            editor.apply()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.setClassName("app.akilesh.nex", "app.akilesh.nex.SplashActivity")
            startActivity(intent)
        }
        return view

    }

    interface OnFragmentInteractionListener

    companion object {
        fun newInstance(): ThemeFragment {
            return ThemeFragment()
        }
    }

}
