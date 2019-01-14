package com.deframe.artapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.*
import com.deframe.artapp.R
import com.deframe.artapp.helper.*
import org.json.JSONObject
import java.net.URL
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.webkit.WebView


/**
 * This class handles the museum list view screen
 */
class ShopFragment : Fragment() {

    private var webView: WebView? = null
    private var window: Window? = null

    companion object {
        val TAG: String = ShopFragment::class.java.simpleName
        fun newInstance() = ShopFragment()
    }

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     * @param inflater
     * @param container parent view group
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_shop)
        super.onCreate(savedInstanceState)
        var view = inflater.inflate(R.layout.fragment_shop, container, false)

        //window!!.requestFeature(Window.FEATURE_PROGRESS)
        webView = view.findViewById(R.id.webView)
        if (webView != null) {

            val webSettings = webView!!.settings
            webSettings.javaScriptEnabled = true

            webSettings.builtInZoomControls = true

            webView!!.webViewClient = WebViewClt(this.requireActivity())
            webView!!.webChromeClient = WebChromeClt(this.requireActivity())

            //           Case 1 .. Direct Url of the page...
            webView!!.loadUrl(getString(R.string.site_url))

            //  Case 2 .. Create your own html page...
            //webView!!.loadData("<html><body>Hello, world!</body></html>", "text/html", "UTF-8")

        }

        return view
    }

    /**
     * Handles onViewCreated actions of the activity
     *
     * @param savedInstanceState
     * @param view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    /*
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.webView!!.canGoBack()) {
            webView!!.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }
    */





}