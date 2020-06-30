package com.example.uilayouttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uilayouttest.base.BaseActivity
import com.example.uilayouttest.chat.ChatActivity
import com.example.uilayouttest.news.NewsContentActivity
import com.example.uilayouttest.news.NewsMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    lateinit var timeChangeReceiver: TimeChangeReceiver

    inner class TimeChangeReceiver: BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Time has changed.", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver =  TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        btnListView.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))
        }

        btnRecyclerView.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }

        btnChatActivity.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        btnFragActivity.setOnClickListener {
            startActivity(Intent(this, FragmentTestActivity::class.java))
        }

        btnNewsActivity.setOnClickListener {
            startActivity(Intent(this, NewsMainActivity::class.java))
        }

        btnSendMyBroadcast.setOnClickListener {
            val intent = Intent("com.example.uilayouttest.MY_BROADCAST")
            intent.setPackage(packageName)
            // 发送标准广播
//            sendBroadcast(intent)

            // 发送有序广播
            sendOrderedBroadcast(intent, null)
        }

        btnForceOffline.setOnClickListener {
            val intent = Intent("com.example.uilayouttest.FORCE_OFFLINE")
            sendBroadcast(intent)
        }

        btnFilePersistence.setOnClickListener {
            startActivity(Intent(this, FilePersistenceTestActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }
}