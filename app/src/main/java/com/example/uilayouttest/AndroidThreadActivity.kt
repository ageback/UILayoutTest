package com.example.uilayouttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_android_thread.*
import kotlin.concurrent.thread

class AndroidThreadActivity : AppCompatActivity() {
    val updateText = 1

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

        btnChangeText.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }
    }
}