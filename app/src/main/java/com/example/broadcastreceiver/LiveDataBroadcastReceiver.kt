package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LiveDataBroadcastReceiver : BroadcastReceiver() {
    private val _textLiveData = MutableLiveData<String>()
    val textLiveData = _textLiveData as LiveData<String>

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            _textLiveData.value = it.getStringExtra(KEY_BROADCAST_TEXT)
        }
    }

    companion object {
        const val LIVE_DATA_BROADCAST_RECEIVER_ACTION = "LIVE_DATA_BROADCAST_RECEIVER_ACTION"
        const val KEY_BROADCAST_TEXT = "KEY_BROADCAST_TEXT"

        fun newIntentFilter() = IntentFilter(LIVE_DATA_BROADCAST_RECEIVER_ACTION)
    }
}