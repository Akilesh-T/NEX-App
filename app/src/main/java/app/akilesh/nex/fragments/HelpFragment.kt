package app.akilesh.nex.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.akilesh.nex.R
import kotlinx.android.synthetic.main.fragment_help.*

class HelpFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        readme.setOnClickListener(this)
        xda.setOnClickListener(this)
        telegram.setOnClickListener(this)
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

    companion object {

        internal const val TAG = "HelpFragmentTag"
    }

}
