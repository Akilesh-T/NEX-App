package app.akilesh.nex.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.akilesh.nex.BuildConfig
import app.akilesh.nex.R
import com.google.android.material.textview.MaterialTextView
import java.io.*


class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        val ver = view.findViewById<MaterialTextView>(R.id.version)
        val versionName = BuildConfig.VERSION_NAME
        ver.text = String.format("%s", versionName)

        val propPath = "/sbin/.magisk/modules/nokia-extensions/module.prop"
        val magiskVer = view.findViewById<MaterialTextView>(R.id.nex_version)
        var modVer = "Unknown"
        if(File(propPath).exists()) {
            try {
                val p = Runtime.getRuntime().exec("sed -n s/^version=//p $propPath | head -n 1")

                val stdInput = BufferedReader(InputStreamReader(p.inputStream) as Reader)
                modVer = stdInput.readLine().trimStart('v')

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        else {
            modVer = "Not found"
            Log.e(TAG, "$propPath doesn't exist")
        }
        magiskVer.text = String.format("%s", modVer)

        return view
    }

    companion object {

        internal const val TAG = "AboutFragmentTag"
    }
}