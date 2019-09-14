package app.akilesh.nex.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.akilesh.nex.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),  View.OnClickListener  {

    private val appName: Map<String, String> = mapOf(
            "com.nbc.AIFloatingTouch.STouchActivity" to "AI Floating Touch",
            "om.evenwell.firewall.TrafficControl" to "App Traffic Control",
            "com.android.settings.Settings\$SecurityDashboardActivity" to "Face Plus Service",
            "com.nbc.gameassistant.GameAssistantActivity" to "Game Assistant",
            "com.android.settings.Settings\$GestureSettingsActivity" to "Gesture overlay",
            "com.android.systemui.keyguard.glance.GlanceSettingsActivity" to "Glance Screen",
            "com.evenwell.cleaner.MainActivity" to "Junk Cleaner",
            "com.nbc.android.screenrecord.ui.ActivitySetting" to "Screen Recorder",
            "com.evenwell.smartboost.activities.MainActivity" to "Smart Boost",
            "com.evenwell.memorycleaner.MainActivity" to "Task Manager",
            "com.evenwell.viruscan.qscanner.VirusScannerActivity" to "Virus Scanner")

    private fun isCallable(intent: Intent): Boolean {
        val activities = context!!.packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY)
        return activities.isNotEmpty()
    }

    private fun launchApp(packageName: String, className: String){

        val intent = Intent(Intent.ACTION_MAIN)
        intent.setClassName(packageName, className)
        if (isCallable(intent))
            startActivity(intent)
        else {
           val toast = Toast.makeText(activity, "${appName[className]} is not installed", Toast.LENGTH_SHORT)
           toast.setGravity(Gravity.BOTTOM, 0, 230)
           toast.show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ai_desc.setOnClickListener(this)
        fw_desc.setOnClickListener(this)
        face_unlock.setOnClickListener(this)
        ga_desc.setOnClickListener(this)
        ges_desc.setOnClickListener(this)
        gl_desc.setOnClickListener(this)
        jc_desc.setOnClickListener(this)
        sr_desc.setOnClickListener(this)
        sb_desc.setOnClickListener(this)
        tm_desc.setOnClickListener(this)
        vs_desc.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.ai_desc -> launchApp("com.nbc.AIFloatingTouch", "com.nbc.AIFloatingTouch.STouchActivity")

            R.id.fw_desc -> launchApp("com.evenwell.firewall", "com.evenwell.firewall.TrafficControl")

            R.id.face_unlock -> launchApp("com.android.settings", "com.android.settings.Settings\$SecurityDashboardActivity")

            R.id.ga_desc -> launchApp("com.nbc.gameassistant", "com.nbc.gameassistant.GameAssistantActivity")

            R.id.ges_desc -> launchApp("com.android.settings", "com.android.settings.Settings\$GestureSettingsActivity")

            R.id.gl_desc -> launchApp("com.android.systemui", "com.android.systemui.keyguard.glance.GlanceSettingsActivity")

            R.id.jc_desc -> launchApp("com.evenwell.cleaner", "com.evenwell.cleaner.MainActivity")

            R.id.sr_desc -> launchApp("com.nbc.android.screenrecord", "com.nbc.android.screenrecord.ui.ActivitySetting")

            R.id.sb_desc -> launchApp("com.evenwell.smartboost", "com.evenwell.smartboost.activities.MainActivity")

            R.id.tm_desc -> launchApp("com.evenwell.memorycleaner", "com.evenwell.memorycleaner.MainActivity")

            R.id.vs_desc -> launchApp("com.evenwell.viruscan", "com.evenwell.viruscan.qscanner.VirusScannerActivity")

        }
    }


    companion object {

        internal const val TAG = "HomeFragmentTag"

    }

}
