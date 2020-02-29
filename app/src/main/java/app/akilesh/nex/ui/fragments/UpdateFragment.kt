package app.akilesh.nex.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.File
import java.io.InputStreamReader

class UpdateFragment: Fragment() {

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
            if(Shell.su("[ -f $gmsUpdatePrefPath ]").exec().isSuccess) {

                val file = File(requireContext().filesDir, "update.xml")
                Shell.su(
                    "cp -af $gmsUpdatePrefPath ${file.absolutePath}",
                    "chmod 664 ${file.absolutePath}"
                ).exec()
                val result = parseXML(file.reader())
                if (result != null) {
                    Log.d("update-url", result)
                    binding.url.apply {
                        text = String.format("%s", result)
                        setTextIsSelectable(true)
                    }
                    binding.hint.apply {
                        visibility = View.VISIBLE
                        text = String.format("%s", resources.getString(R.string.hint))
                    }
                    binding.check.visibility = View.GONE
                }
            }
            else {
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

    private fun parseXML(reader: InputStreamReader): String? {
        val xmlPullParserFactory = XmlPullParserFactory.newInstance()
        xmlPullParserFactory.isNamespaceAware = true
        val xmlPullParser = xmlPullParserFactory.newPullParser()
        xmlPullParser.setInput(reader)
        var eventType = xmlPullParser.eventType
        var tag: String? = null
        var attr: String? = null
        var updateURL: String? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if ("map" != xmlPullParser.name) {
                    tag = xmlPullParser.name
                    attr = xmlPullParser.getAttributeValue(null, "name")
                }
            }
            else if (eventType == XmlPullParser.END_TAG) tag = null
            else if (eventType == XmlPullParser.TEXT) {
                if (tag != null && attr == "control.installation.current_update_url") {
                    updateURL = xmlPullParser.text
                }
            }
            eventType = xmlPullParser.next()
        }
        return updateURL
    }
}
