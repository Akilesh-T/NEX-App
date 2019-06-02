package app.akilesh.nex

import android.app.Application
import android.preference.PreferenceManager

class ThemeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themePref = sharedPreferences.getString("themePref", ThemeHelper().default )
        ThemeHelper().applyTheme(themePref)
    }
}