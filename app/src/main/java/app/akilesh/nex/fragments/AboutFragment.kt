package app.akilesh.nex.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.akilesh.nex.BuildConfig
import app.akilesh.nex.R
import com.topjohnwu.superuser.Shell
import kotlinx.android.synthetic.main.fragment_about.*
import java.io.*


class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        version.text = BuildConfig.VERSION_NAME

        val propPath = "/sbin/.magisk/modules/nokia-extensions/module.prop"
        if(File(propPath).exists()) {
            val out = Shell.sh("sed -n s/^version=//p $propPath | head -n 1").exec().out
            nex_version.text = out.component1().drop(1)
        }
        else {
            nex_version.text = String.format("%s", "Module not installed")
            Log.e(TAG, "$propPath doesn't exist")
        }

    }

    companion object {

        internal const val TAG = "AboutFragmentTag"
    }
}