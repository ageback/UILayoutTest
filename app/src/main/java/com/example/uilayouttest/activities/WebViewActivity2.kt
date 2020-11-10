package com.example.uilayouttest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.uilayouttest.R
import com.example.uilayouttest.`interface`.WebAppInterface

class WebViewActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view2)

        val webView2: WebView = findViewById(R.id.webview2)
        webView2.loadUrl("http://oa.wesoft.net.cn/wv2.html")
        webView2.settings.javaScriptEnabled = true
        webView2.addJavascriptInterface(WebAppInterface(this), "Android")
    }
}