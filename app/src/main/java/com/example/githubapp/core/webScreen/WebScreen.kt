package com.example.githubapp.core.webScreen

import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import timber.log.Timber
import timber.log.Timber.Forest

@Composable
fun WebScreen(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)
             object : WebViewClient() {
                 override fun shouldOverrideUrlLoading(
                     view: WebView?,
                     request: WebResourceRequest?,
                 ): Boolean {
                     Timber.e("REQUEST: $request")
                     if(request?.url?.toString()?.contains("example://login") == true) {
                         Timber.e("REQUEST IS CAUGHT")
                       return false
                     }
                     Timber.e("REQUEST IS NOT CAUGHT")
                     return super.shouldOverrideUrlLoading(view, request)
                 }
             }
        }
    }, update = {
        it.loadUrl(url)
    })
}
