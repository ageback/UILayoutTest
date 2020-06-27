package com.example.uilayouttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uilayouttest.chat.ChatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

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
    }
}