package app.akilesh.nex.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.akilesh.nex.R


class HomeFragment : Fragment(),  View.OnClickListener  {
    private lateinit var ai:Button
    private lateinit var fw:Button
    private lateinit var ga:Button
    private lateinit var jc:Button
    private lateinit var sr:Button
    private lateinit var sb:Button
    private lateinit var tm:Button
    private lateinit var vs:Button

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
        else
            Toast.makeText(activity, "$packageName is not installed", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.home_fragment, container, false)

        ai = view.findViewById(R.id.ai_btn)
        ai.setOnClickListener(this)

        fw = view.findViewById(R.id.fw_btn)
        fw.setOnClickListener(this)

        ga = view.findViewById(R.id.ga_btn)
        ga.setOnClickListener(this)

        jc = view.findViewById(R.id.jc_btn)
        jc.setOnClickListener(this)

        sr = view.findViewById(R.id.sr_btn)
        sr.setOnClickListener(this)

        sb = view.findViewById(R.id.sb_btn)
        sb.setOnClickListener(this)

        tm = view.findViewById(R.id.tm_btn)
        tm.setOnClickListener(this)

        vs = view.findViewById(R.id.vs_btn)
        vs.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ai_btn -> {
                launchApp("com.nbc.AIFloatingTouch", "com.nbc.AIFloatingTouch.STouchActivity")
            }

            R.id.fw_btn -> {
                launchApp("com.evenwell.firewall", "com.evenwell.firewall.TrafficControl")
            }

            R.id.ga_btn -> {
                launchApp("com.nbc.gameassistant", "com.nbc.gameassistant.GameAssistantActivity")
            }

            R.id.jc_btn -> {
                launchApp("com.evenwell.cleaner", "com.evenwell.cleaner.MainActivity")
            }

            R.id.sr_btn -> {
                launchApp("com.nbc.android.screenrecord", "com.nbc.android.screenrecord.ui.ActivitySetting")
            }

            R.id.sb_btn -> {
                launchApp("com.evenwell.smartboost", "com.evenwell.smartboost.activities.MainActivity")
            }

            R.id.tm_btn -> {
                launchApp("com.evenwell.memorycleaner", "com.evenwell.memorycleaner.MainActivity")
            }

            R.id.vs_btn -> {
                launchApp("com.evenwell.viruscan", "com.evenwell.viruscan.qscanner.VirusScannerActivity")
            }
        }
    }

    interface OnFragmentInteractionListener

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

}
