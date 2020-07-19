package com.example.uilayouttest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uilayouttest.R
import kotlinx.android.synthetic.main.activity_network_test.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class NetworkTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_test)
        btnSendHttpRequest.setOnClickListener {
            sendRequestWithHttpURLConnection()
        }

        btnSendOkHttpRequest.setOnClickListener {
            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithHttpURLConnection()
    {
        thread {
            var connection : HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream

                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception)
            {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            txtHttpResponseText.text = response
        }
    }

    private fun sendRequestWithOkHttp()
    {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.baidu.com")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if(responseData!=null)
                {
                    showResponse(responseData)
                }
            } catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }
}