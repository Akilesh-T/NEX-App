package app.akilesh.nex


import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import app.akilesh.nex.fragments.BottomNavigationDrawerFragment
import app.akilesh.nex.fragments.HomeFragment
import app.akilesh.nex.fragments.SettingsFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var currentNightMode: Int = -1
    private val brand: String = Build.BRAND
    private lateinit var bottomAppBar: BottomAppBar


    override fun onCreate(savedInstanceState: Bundle?) {

        if(brand != "Nokia") {
            Toast.makeText(this, "This app is only for Nokia phones!", Toast.LENGTH_LONG).show()
            this.finishAffinity()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomAppBar = findViewById(R.id.bar)
        setSupportActionBar(bottomAppBar)

        val decorView = window.decorView
        decorView.systemUiVisibility = FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                val colorAccent = ContextCompat.getColor(this, R.color.colorAccent)
                window.navigationBarColor = colorAccent
                window.statusBarColor = colorAccent
                decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }

            Configuration.UI_MODE_NIGHT_YES -> {
                val colorSurface = Color.parseColor("#121212")
                window.statusBarColor = colorSurface
                window.navigationBarColor = colorSurface
            }

        }

        if(savedInstanceState == null)
            showFragment(HomeFragment.TAG)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            showFragment(HomeFragment.TAG)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottom_appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }

            R.id.app_bar_settings -> {
                showFragment(SettingsFragment.TAG)
            }
        }
        return true
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


