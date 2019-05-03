package app.akilesh.nex

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import app.akilesh.nex.fragments.AboutFragment
import app.akilesh.nex.fragments.DeviceFragment
import app.akilesh.nex.fragments.HelpFragment
import app.akilesh.nex.fragments.HomeFragment
import app.akilesh.nex.fragments.ThemeFragment


import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceManager


import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Objects

import android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS


class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener, AboutFragment.OnFragmentInteractionListener, HelpFragment.OnFragmentInteractionListener, ThemeFragment.OnFragmentInteractionListener, DeviceFragment.OnFragmentInteractionListener {
    private var currentNightMode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val themeID = sharedPref.getInt("ThemePrefs", -1)
        AppCompatDelegate.setDefaultNightMode(themeID)
        currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        Objects.requireNonNull<ActionBar>(supportActionBar).displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.abs_layout)

        val decorView = window.decorView
        decorView.systemUiVisibility = FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS

        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO ->
                // Night mode is not active, we're in day time
                decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.menu_home -> fragment = HomeFragment.newInstance()
                R.id.menu_info -> fragment = AboutFragment.newInstance()
                R.id.menu_help -> fragment = HelpFragment.newInstance()

                R.id.menu_invert -> fragment = ThemeFragment.newInstance()

                R.id.device_info -> fragment = DeviceFragment.newInstance()
            }
            if (fragment != null) {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frameLayout, fragment)
                fragmentTransaction.commit()
            }
            true
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, HomeFragment.newInstance())
        fragmentTransaction.commit()

    }
}


