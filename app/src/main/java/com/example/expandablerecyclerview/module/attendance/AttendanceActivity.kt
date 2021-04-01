package com.example.expandablerecyclerview.module.attendance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.expandablerecyclerview.R
import com.example.expandablerecyclerview.module.category.CategoryActivity
import kotlinx.android.synthetic.main.activity_attendance.*

class AttendanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("http://gandhionline.in/BEESERP/Login.aspx?ReturnUrl=%2fBEESERP%2f")
        val webSettings=webView.settings
        webSettings.javaScriptEnabled=true
       webSettings.supportZoom()
        webSettings.builtInZoomControls=true
        webSettings.useWideViewPort=true

    }

    override fun onBackPressed() {

        startActivity(Intent(this,CategoryActivity::class.java))
        finish()
    }
}