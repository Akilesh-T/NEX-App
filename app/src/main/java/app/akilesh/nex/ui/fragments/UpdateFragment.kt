package app.akilesh.nex.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.akilesh.nex.Const.Path.gmsUpdatePrefPath
import app.akilesh.nex.R
import app.akilesh.nex.databinding.FragmentUpdateBinding
import app.akilesh.nex.ui.adapter.UpdateHistoryAdapter
import app.akilesh.nex.utils.UpdateHistoryUtil
import com.topjohnwu.superuser.Shell

class UpdateFragment: Fragment() {

    private val cmd: String = "[ -f $gmsUpdatePrefPath ] && cat $gmsUpdatePrefPath"
    private lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val updateHistoryUtil = UpdateHistoryUtil()
        if(updateHistoryUtil.check()) {
            updateHistoryUtil.init()
            binding.historyRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = UpdateHistoryAdapter(updateHistoryUtil.updateList)
            }
        }
        else binding.update.visibility = View.GONE

        if(Shell.rootAccess()) {
            val output = Shell.su(cmd).exec().out
            val regex = "(https)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(zip)".toRegex()
            val matchResult = regex.find(output.toString())
            val res = matchResult?.value

            if (matchResult != null) {
                binding.url.apply {
                    setTextIsSelectable(true)
                    text = res
                }
                binding.check.visibility = View.GONE

                binding.hint.apply {
                    visibility = View.VISIBLE
                    text = resources.getString(R.string.hint)
                }
            } else {
                binding.url.text = String.format("%s", "No updates available.")
                binding.check.setOnClickListener {
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.setClassName("com.google.android.gms","com.google.android.gms.update.SystemUpdateActivity")
                    startActivity(intent)
                }
            }
        }
        else {
            binding.url.text = String.format("%s", "Unable to get root access. Take a bug report or use <i>adb logcat | grep \"packages/ota-api\"</i> to get the ota link.")
        }

    }
    companion object {
        internal const val TAG = "UpdateFragment"
    }
}