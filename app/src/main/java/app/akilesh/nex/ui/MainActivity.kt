package app.akilesh.nex.ui


import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.akilesh.nex.R
import app.akilesh.nex.databinding.ActivityMainBinding
import app.akilesh.nex.ui.fragments.BottomNavigationDrawerFragment
import app.akilesh.nex.ui.fragments.HomeFragment
import app.akilesh.nex.ui.fragments.SettingsFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        val brand: String = Build.BRAND
        if(brand != "Nokia") {
            Toast.makeText(this, "This app is only for Nokia phones!", Toast.LENGTH_LONG).show()
            this.finishAffinity()
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val decorView = window.decorView
        decorView.systemUiVisibility = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                decorView.systemUiVisibility =
                    SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                window.navigationBarColor = Color.TRANSPARENT
            }
        }

        if(savedInstanceState == null)
            showFragment(HomeFragment.TAG)

        binding.fab.setOnClickListener {
            showFragment(HomeFragment.TAG)
        }

        binding.bar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.app_bar_settings -> {
                    showFragment(SettingsFragment.TAG)
                }
            }
            true
        }

        binding.bar.setNavigationOnClickListener {
            val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
            bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
        }
    }

    private fun showFragment(tag: String) {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            when (tag) {
                HomeFragment.TAG -> {
                    fragment = HomeFragment()
                }
                SettingsFragment.TAG -> {
                    fragment = SettingsFragment()
                }
            }
        }

        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, it, tag)
                .commit()
        }
    }

}


