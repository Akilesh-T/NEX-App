package app.akilesh.nex.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import app.akilesh.nex.R
import java.io.File

@SuppressLint("SdCardPath")
class UpdateFragment: Fragment() {

    private lateinit var textView: TextView
    private lateinit var hintText: TextView
    private lateinit var url: String
    private lateinit var cmd: String

    private var dataFile = "/data/data/com.google.android.gms/shared_prefs/com.google.android.gms.update.storage.xml"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        textView = view.findViewById(R.id.url)
        hintText = view.findViewById(R.id.hint)

        if(File(dataFile).exists()) {
            cmd = "cat $dataFile"
            val msg = "Reading from $dataFile"
            Log.d(TAG, msg)

            val process = Runtime.getRuntime().exec("su")
            val `in` = process.inputStream
            val out = process.outputStream
            out.write(cmd.toByteArray())
            out.flush()
            out.close()
            process.waitFor()

            if (process.exitValue() != 0) {
                Log.e(TAG, "Failed to obtain root")
                textView.text = String.format("%s", "Unable to get root access. Take a bug report or use adb logcat to get the ota link.")
            } else {
                var ch: Int
                val sb = StringBuilder()
                while (`in`.read().let { ch = it; it != -1 })
                    sb.append(ch.toChar())
                url = sb.toString().trim()

                val regex = "(https)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(zip)".toRegex()
                val matchResult = regex.find(url)
                val result = matchResult?.value

                if (matchResult != null) {
                    textView.setTextIsSelectable(true)
                    textView.text = String.format("%s", result)
                    hintText.text = String.format("%s", resources.getString(R.string.hint))
                } else
                    textView.text = String.format("%s", "No updates available.")
            }
        }

        return view
    }

    companion object {
        internal const val TAG = "UpdateFragmentTag"
    }
}