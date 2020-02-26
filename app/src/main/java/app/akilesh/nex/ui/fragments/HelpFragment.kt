package app.akilesh.nex.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.akilesh.nex.Const.Url
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
                openURL(Url.githubReadMe)
            }

            R.id.xda -> {
                openURL(Url.xdaThread)
            }

            R.id.telegram -> {
                openURL(Url.telegramGroup)
            }
        }
    }

    companion object {

        internal const val TAG = "HelpFragment"
    }

}
