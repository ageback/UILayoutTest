package com.example.uilayouttest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.uilayouttest.base.BaseActivity
import com.example.uilayouttest.chat.ChatActivity
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

        btnSharedPreferences.setOnClickListener {
            startActivity(Intent(this, SharedPreferencesTestActivity::class.java))
        }

        btnDatabaseTest.setOnClickListener {
            startActivity(Intent(this, DatabaseTestActivity::class.java))
        }

        btnMakeCallTest.setOnClickListener {
            startActivity(Intent(this, RuntimePermissionTestActivity::class.java))
        }

        btnReadContacts.setOnClickListener {
            startActivity(Intent(this, ContactsTestActivity::class.java))

        }

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)

            val channel2 = NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel2)
        }
        btnNotice.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("通知标题")
               // .setContentText("通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容")
                .setStyle(NotificationCompat.BigTextStyle().bigText("通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容"))
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image)))
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            manager.notify(1, notification)
        }

        btnTakePhotoActivity.setOnClickListener {
            startActivity(Intent(this, CameraAlbumTestActivity::class.java))
        }

        btnPlayAudioActivity.setOnClickListener {
            startActivity(Intent(this, PlayAudioActivity::class.java))
        }

        btnAndroidThreadActivity.setOnClickListener {
            startActivity(Intent(this, ServiceAndThreadActivity::class.java))
        }

        btnNetworkActivity.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }
}