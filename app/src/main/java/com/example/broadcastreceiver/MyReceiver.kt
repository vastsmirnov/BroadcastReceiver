package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(
            context,
            intent.getStringExtra(ResultFragment.KEY_BROADCAST_TEXT),
            Toast.LENGTH_SHORT
        ).show()
    }
}