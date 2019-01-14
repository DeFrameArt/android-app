package com.deframe.artapp.helper

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class WebViewClt internal constructor(private val activity: FragmentActivity) : WebViewClient() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url: String = request?.url.toString();

        if (url.contains("https://tutorialwing.com", false))
            return false

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(intent)
        return true
    }

    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
        if (url.contains("https://tutorialwing.com"))
            return false

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(intent)
        return true
    }

    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
        Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
    }
}
