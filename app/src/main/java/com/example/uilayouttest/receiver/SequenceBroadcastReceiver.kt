package com.example.uilayouttest.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class SequenceBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "received in SequenceBroadcastReceiver", Toast.LENGTH_SHORT).show()
    }
}