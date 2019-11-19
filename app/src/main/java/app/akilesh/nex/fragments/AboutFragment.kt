package app.akilesh.nex.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.akilesh.nex.BuildConfig
import app.akilesh.nex.Const.Path.modulePath
import app.akilesh.nex.R
import com.topjohnwu.superuser.Shell
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        version.text = BuildConfig.VERSION_NAME

        val outputs = mutableListOf<String>()
        val propPath = modulePath + "module.prop"
        Shell.su("[ -f $propPath ] && echo y").to(outputs).exec()
        if(outputs.isNotEmpty() && outputs.component1().isNotBlank()) {
            Shell.su("sed -n s/^version=//p $propPath | head -n 1").to(outputs).exec()
            nex_version.text = outputs.component2().drop(1)
        }
        else {
            nex_version.text = String.format("%s", "Module not installed")
            Log.e(TAG, "$propPath doesn't exist")
        }

    }

    companion object {

        internal const val TAG = "AboutFragment"
    }
}