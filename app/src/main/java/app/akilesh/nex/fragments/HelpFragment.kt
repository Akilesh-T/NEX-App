package app.akilesh.nex.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import app.akilesh.nex.R

class HelpFragment : Fragment(), View.OnClickListener {
    private lateinit var readMe: Button
    private lateinit var xda: Button
    private lateinit var telegramGroup: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.help_fragment, container, false)

        readMe = view.findViewById(R.id.readme)
        readMe.setOnClickListener(this)

        xda = view.findViewById(R.id.xda)
        xda.setOnClickListener(this)

        telegramGroup = view.findViewById(R.id.telegram)
        telegramGroup.setOnClickListener(this)

        return view
    }

    private fun openURL(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onClick(v: View?){
        when(v?.id){
            R.id.readme -> {
                openURL("https://github.com/Magisk-Modules-Repo/nokia-extensions/blob/master/README.md#how-tos")
            }

            R.id.xda -> {
                openURL("https://forum.xda-developers.com/nokia-7-plus/themes/magisk-module-nokia-extensions-android-t3865438?goto=newpost")
            }

            R.id.telegram -> {
                openURL("https://t.me/NokiaExtensions")
            }
        }
    }

    interface OnFragmentInteractionListener

    companion object {

        fun newInstance(): HelpFragment {
            return HelpFragment()
        }
    }

}
