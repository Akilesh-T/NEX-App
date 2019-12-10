package app.akilesh.nex.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.akilesh.nex.Const.Path.gmsUpdatePrefPath
import app.akilesh.nex.R
import app.akilesh.nex.adapter.UpdateHistoryAdapter
import app.akilesh.nex.utils.UpdateHistoryUtil
import com.topjohnwu.superuser.Shell
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment: Fragment() {

    private val cmd: String = "[ -f $gmsUpdatePrefPath ] && cat $gmsUpdatePrefPath"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val updateHistoryUtil = UpdateHistoryUtil()
        if(updateHistoryUtil.check()) {
            updateHistoryUtil.init()
            history_recyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = UpdateHistoryAdapter(updateHistoryUtil.updateList)
            }
        }
        else update.visibility = View.GONE

        if(Shell.rootAccess()) {
            val output = Shell.su(cmd).exec().out
            val regex = "(https)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(zip)".toRegex()
            val matchResult = regex.find(output.toString())
            val res = matchResult?.value

            if (matchResult != null) {
                url.setTextIsSelectable(true)
                url.text = res
                check.visibility = View.GONE
                hint.visibility = View.VISIBLE
                hint.text = resources.getString(R.string.hint)
            } else {
                url.text = String.format("%s", "No updates available.")
                check.setOnClickListener {
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.setClassName("com.google.android.gms","com.google.android.gms.update.SystemUpdateActivity")
                    startActivity(intent)
                }
            }
        }
        else {
            url.text = String.format("%s", "Unable to get root access. Take a bug report or use <i>adb logcat | grep \"packages/ota-api\"</i> to get the ota link.")
        }

    }
    companion object {
        internal const val TAG = "UpdateFragment"
    }
}