package app.akilesh.nex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import app.akilesh.nex.BuildConfig
import app.akilesh.nex.R

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.about_fragment, container, false)
        val ver = view.findViewById<TextView>(R.id.version)
        val versionName = BuildConfig.VERSION_NAME
        ver.text = String.format("%s", versionName)
        return view
    }

    interface OnFragmentInteractionListener

    companion object {

        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }
}