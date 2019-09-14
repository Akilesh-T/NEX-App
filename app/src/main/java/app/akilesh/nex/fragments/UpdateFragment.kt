package app.akilesh.nex.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.akilesh.nex.R
import com.topjohnwu.superuser.Shell
import kotlinx.android.synthetic.main.fragment_update.*

@SuppressLint("SdCardPath")
class UpdateFragment: Fragment() {

    private var dataFile = "/data/data/com.google.android.gms/shared_prefs/com.google.android.gms.update.storage.xml"
    private val cmd: String = "[ -r $dataFile ] && cat $dataFile"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(Shell.rootAccess()) {
            val output = Shell.su(cmd).exec().out
            val regex = "(https)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(zip)".toRegex()
            val matchResult = regex.find(output.toString())
            val res = matchResult?.value

            if (matchResult != null) {
                url.setTextIsSelectable(true)
                url.text = res
                hint.text = resources.getString(R.string.hint)
            } else
                url.text = String.format("%s", "No updates available.")
        }
        else {
            url.text = String.format("%s", "Unable to get root access. Take a bug report or use `adb logcat | grep \"packages/ota-api\"` to get the ota link.")
        }
    }
    companion object {
        internal const val TAG = "UpdateFragmentTag"
    }
}