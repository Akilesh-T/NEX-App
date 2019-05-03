package app.akilesh.nex.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import app.akilesh.nex.R

class HelpFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.help_fragment, container, false)
        val mWebView = view.findViewById<WebView>(R.id.webview)

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.webViewClient = WebViewClient()

        // Enable Javascript
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true

        // REMOTE RESOURCE
        mWebView.loadUrl("https://github.com/Magisk-Modules-Repo/nokia-extensions/blob/master/README.md#how-tos")


        mWebView.setOnKeyListener(View.OnKeyListener { _ , keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.action == MotionEvent.ACTION_UP
                    && mWebView.canGoBack()) {
                mWebView.goBack()
                return@OnKeyListener true
            }
            false
        })


        return view
    }


    interface OnFragmentInteractionListener

    companion object {

        fun newInstance(): HelpFragment {
            return HelpFragment()
        }
    }

}
