package app.akilesh.nex.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.io.IOException
import androidx.fragment.app.Fragment
import app.akilesh.nex.R

import android.content.ContentValues.TAG


class DeviceFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.device_fragment, container, false)

        val brand = Build.BRAND
        val deviceBrand = view.findViewById<TextView>(R.id.brand)
        deviceBrand.text = String.format("%s", brand)

        val model = Build.MODEL
        val deviceModel = view.findViewById<TextView>(R.id.model)
        deviceModel.text = String.format("%s", model)

        val code = Build.DEVICE
        val deviceCodeName = view.findViewById<TextView>(R.id.codeName)
        deviceCodeName.text = String.format("%s", code)

        var buildVer = "Unknown"

        try {
            val process = Runtime.getRuntime().exec("su")
            val `in` = process.inputStream
            val out = process.outputStream
            val cmd = "[ -r /proc/fver ] && head -n 1 /proc/fver"
            out.write(cmd.toByteArray())
            out.flush()
            out.close()
            process.waitFor()

            if (process.exitValue() != 0) {
                Log.e(TAG, "Failed to obtain root")
            } else {
                var ch: Int
                val sb = StringBuilder()
                while (`in`.read().let { ch = it; it != -1 })
                    sb.append(ch.toChar())
                buildVer = sb.toString().substring(4, 23)
            }
            buildVer = buildVer.trim { it <= ' ' }

        } catch (e: IOException) {
            Log.e(TAG, "IOException, " + e.message)

        } catch (e: InterruptedException) {
            Log.e(TAG, "InterruptedException, " + e.message)
        }

        val build = view.findViewById<TextView>(R.id.buildVersion)
        if (buildVer.isNotEmpty()) {
            build.text = String.format("%s", buildVer)
        } else
            build.text = String.format("%s", "Not found")


        var skuid = "Unknown"

        try {
            val process = Runtime.getRuntime().exec("su")
            val `in` = process.inputStream
            val out = process.outputStream
            val cmd = "[ -r /proc/cda/skuid ] && head -n 1 /proc/cda/skuid"
            out.write(cmd.toByteArray())
            out.flush()
            out.close()
            process.waitFor()

            if (process.exitValue() != 0) {
                Log.e(TAG, "Failed to obtain root")
            } else {
                var ch: Int
                val sb = StringBuilder()
                while (`in`.read().let { ch = it; it != -1 })
                    sb.append(ch.toChar())
                skuid = sb.toString()
            }
            skuid = skuid.trim { it <= ' ' }

        } catch (e: IOException) {
            Log.e(TAG, "IOException, " + e.message)

        } catch (e: InterruptedException) {
            Log.e(TAG, "InterruptedException, " + e.message)
        }

        val textView = view.findViewById<TextView>(R.id.skuid)
        if (skuid.isNotEmpty()) {
            textView.text = String.format("%s", skuid)
        } else
            textView.text = String.format("%s", "Not found")

        return view
    }


    interface OnFragmentInteractionListener

    companion object {

        fun newInstance(): DeviceFragment {
            return DeviceFragment()
        }
    }

}
