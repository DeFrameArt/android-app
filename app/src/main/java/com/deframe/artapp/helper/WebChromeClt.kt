package com.deframe.artapp.helper

import android.app.Activity
import android.support.v4.app.FragmentActivity
import android.webkit.WebChromeClient
import android.webkit.WebView

class WebChromeClt internal constructor(private val activity: FragmentActivity) : WebChromeClient() {

    override fun onProgressChanged(view: WebView, progress: Int) {
        // Activities and WebViews measure progress with different scales.
        // The progress meter will automatically disappear when we reach 100%
        activity.setProgress(progress * 1000)
    }
}