package com.example.expandablerecyclerview.module.results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.expandablerecyclerview.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    val url="http://www.gandhionline.in/BETE-PORTAL/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        resultWebView.webViewClient= WebViewClient()
        resultWebView.loadUrl(url)
        val webSettings=resultWebView.settings
        resultWebView.setInitialScale(110)
        webSettings.loadWithOverviewMode=true
        webSettings.useWideViewPort=true
        webSettings.supportZoom()
        webSettings.builtInZoomControls=true
        webSettings.displayZoomControls=true
        webSettings.javaScriptEnabled=true
    }

}