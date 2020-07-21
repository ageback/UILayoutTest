package com.example.uilayouttest.`interface`

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}