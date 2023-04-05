package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import org.greenrobot.eventbus.EventBus

class EventBusBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        intent.getStringExtra(KEY_BROADCAST_TEXT)?.let {
            EventBus.getDefault().post(TextEvent(it))
        }
    }

    companion object {
        const val EVENT_BUS_BROADCAST_RECEIVER_ACTION = "EVENT_BUS_BROADCAST_RECEIVER_ACTION"
        const val KEY_BROADCAST_TEXT = "KEY_BROADCAST_TEXT"

        fun newIntentFilter() = IntentFilter(EVENT_BUS_BROADCAST_RECEIVER_ACTION)
    }
}