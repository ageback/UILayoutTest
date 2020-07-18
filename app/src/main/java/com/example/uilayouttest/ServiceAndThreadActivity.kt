package com.example.uilayouttest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import com.example.uilayouttest.service.MyService
import kotlinx.android.synthetic.main.activity_android_thread.*
import kotlin.concurrent.thread

class ServiceAndThreadActivity : AppCompatActivity() {
    val updateText = 1

    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                updateText -> txtChangeText.text="Nice to meet you"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_thread)

        btnBindMyService.setOnClickListener {
            bindService(Intent(this, MyService::class.java), connection, Context.BIND_AUTO_CREATE)
        }

        btnUnbindMyService.setOnClickListener {
            unbindService(connection)
        }
        
        btnChangeText.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }

        btnStartMyService.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }

        btnStopMyService.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }
    }
}