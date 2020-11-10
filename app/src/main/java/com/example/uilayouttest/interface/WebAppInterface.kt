package com.example.uilayouttest.`interface`

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface
import android.widget.Toast
import com.example.uilayouttest.activities.WebViewActivity1
import com.example.uilayouttest.activities.WebViewActivity2

/** Instantiate the interface and set the context  */
class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun startWebView2()
    {
        (mContext as WebViewActivity1).startActivityForResult(Intent(mContext, WebViewActivity2::class.java),0)
    }

    @JavascriptInterface
    fun closeWebView2()
    {
        val result = Intent()
        result.putExtra("ResultCode","WCP")
        (mContext as WebViewActivity2).setResult(RESULT_OK, result)
        (mContext as WebViewActivity2).finish()

    }
}