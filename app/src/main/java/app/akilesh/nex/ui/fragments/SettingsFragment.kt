package app.akilesh.nex.ui.fragments

import app.akilesh.nex.utils.ThemeUtil
import androidx.preference.Preference
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import app.akilesh.nex.R


class SettingsFragment : PreferenceFragmentCompat()  {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val themePreference = findPreference<ListPreference>("themePref")
        if (themePreference != null) {
            themePreference.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
                val themeOption = newValue as String
                ThemeUtil().applyTheme(themeOption)
                true
            }
        }
    }

    companion object {

        internal const val TAG = "SettingsFragment"
    }
}