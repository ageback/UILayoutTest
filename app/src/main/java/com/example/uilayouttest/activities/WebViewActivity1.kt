package com.example.uilayouttest.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import com.example.uilayouttest.R
import com.example.uilayouttest.`interface`.WebAppInterface

class WebViewActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view1)

        val webView1: WebView = findViewById(R.id.webview1)
        webView1.loadUrl("http://oa.wesoft.net.cn/wv1.html")
        webView1.settings.javaScriptEnabled = true
        webView1.addJavascriptInterface(WebAppInterface(this), "Android")


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0)
        {
            if(resultCode== RESULT_OK)
            {
                Toast.makeText(this, "WebView2闭关了", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


