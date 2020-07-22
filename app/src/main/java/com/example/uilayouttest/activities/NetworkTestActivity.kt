package com.example.uilayouttest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.uilayouttest.App
import com.example.uilayouttest.R
import com.example.uilayouttest.`interface`.AppService
import com.example.uilayouttest.`interface`.HttpCallbackListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_android_thread.*
import kotlinx.android.synthetic.main.activity_network_test.*
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class NetworkTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_test)
        btnSendHttpRequest.setOnClickListener {
//            sendRequestWithHttpURLConnection()
//            HttpUtil.sendHttpRequest("http://oa.wesoft.net.cn/get_data.json", object: HttpCallbackListener{
            HttpUtil.sendHttpRequest("http://oa.wesoft.net.cn/wcp/xmlservice", object: HttpCallbackListener{
                override fun onFinish(response: String) {
//                    parseJSONWithGSON(response)
                    txtHttpResponseText.text=response
                }

                override fun onError(e: Exception) {
                    e.printStackTrace()
                }

            })
        }


        btnSendOkHttpRequest.setOnClickListener {
//            sendRequestWithOkHttp()
            HttpUtil.sendOkHttpRequest("http://oa.wesoft.net.cn/get_data.json", object: okhttp3.Callback{
                override fun onResponse(call: Call, response: Response) {
                    parseJSONWithGSON(response.body()?.string())
                }

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
            })
        }

        btnGetAppData.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://oa.wesoft.net.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService = retrofit.create(AppService::class.java)
            appService.getAppData().enqueue(object: Callback<List<App>>{
                override fun onResponse(
                    call: retrofit2.Call<List<App>>,
                    response: retrofit2.Response<List<App>>
                ) {
                    val list = response.body()
                    if(list != null)
                    {
                        for(app in list){
                            Log.d("NetworkTestActivity", "id is ${app.id}")
                            Log.d("NetworkTestActivity", "name is ${app.name}")
                            Log.d("NetworkTestActivity", "version is ${app.version}")
                        }
                    }
                }

                override fun onFailure(call: retrofit2.Call<List<App>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
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
                    .url("http://oa.wesoft.net.cn/get_data.json")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body()?.string()
                if(responseData!=null)
                {
//                    showResponse(responseData)
//                    parseXMLWithPull(responseData)
                    parseJSONWithGSON(responseData)
                }
            } catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }

    private fun parseJSONWithGSON(jsonData: String?) {
        val gson = Gson()
        val typeOf = object : TypeToken<List<App>>() {}.type
        val appList = gson.fromJson<List<App>>(jsonData, typeOf)
        for (app in appList)
        {
            Log.d("NetworkTestActivity", "id is ${app.id}")
            Log.d("NetworkTestActivity", "name is ${app.name}")
            Log.d("NetworkTestActivity", "version is ${app.version}")
        }
    }

    private fun parseXMLWithPull(xmlData: String) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var id=""
            var name=""
            var version=""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if("app" == nodeName) {
                            Log.d("NetworkTestActivity", "id is $id")
                            Log.d("NetworkTestActivity", "name is $name")
                            Log.d("NetworkTestActivity", "version is $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    object HttpUtil {
        fun sendHttpRequest(address: String, listener: HttpCallbackListener) {
            thread {
                var connection: HttpURLConnection? = null
                try {
                    val response = StringBuilder()
                    val url = URL(address)
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
                    listener.onFinish(response.toString())
                } catch (e: Exception) {
                    e.printStackTrace()
                    listener.onError(e)
                } finally {
                    connection?.disconnect()
                }
            }
        }

        fun sendOkHttpRequest(address: String, callback: okhttp3.Callback)
        {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(address)
                .build()
            client.newCall(request).enqueue(callback)
        }
    }
}