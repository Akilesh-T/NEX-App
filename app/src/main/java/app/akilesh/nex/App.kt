package app.akilesh.nex

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.topjohnwu.superuser.Shell


open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themePref = sharedPreferences.getString("themePref", ThemeHelper().default)
        ThemeHelper().applyTheme(themePref)

    }

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        Shell.Config.setFlags(Shell.FLAG_REDIRECT_STDERR)
        Shell.Config.verboseLogging(true)
        Shell.Config.setTimeout(10)
    }
}
