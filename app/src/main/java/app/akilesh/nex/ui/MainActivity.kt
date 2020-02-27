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
import androidx.navigation.fragment.NavHostFragment
import app.akilesh.nex.R
import app.akilesh.nex.databinding.ActivityMainBinding
import app.akilesh.nex.ui.fragments.BottomNavigationDrawerFragment


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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.fab.setOnClickListener {
            navController.navigate(R.id.homeFragment)
        }

        binding.bar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.app_bar_settings -> {
                    navController.navigate(R.id.settingsFragment)
                }
            }
            true
        }

        binding.bar.setNavigationOnClickListener {
            val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
            bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
        }
    }
}


