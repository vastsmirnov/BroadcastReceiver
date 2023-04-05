package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.broadcastreceiver.databinding.FragmentResultBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var objectBroadcastReceiver: BroadcastReceiver

    private val liveDataBroadCastReceiver = LiveDataBroadcastReceiver()
    private val eventBusBroadcastReceiver = EventBusBroadCastReceiver()

    private var objectIntentFilter = IntentFilter(OBJECT_BROADCAST_RECEIVER_ACTION)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(
            inflater,
            container,
            false
        )

        objectBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.let {
                    binding.messageObjectMtv.text = it.getStringExtra(KEY_BROADCAST_TEXT)
                }
            }
        }

        liveDataBroadCastReceiver.textLiveData.observe(viewLifecycleOwner) {
            binding.messageLifeDataMtv.text = it
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        registerBroadCastReceivers()
    }

    private fun registerBroadCastReceivers() {
        requireContext().registerReceiver(objectBroadcastReceiver, objectIntentFilter)

        requireContext().registerReceiver(
            liveDataBroadCastReceiver,
            LiveDataBroadcastReceiver.newIntentFilter()
        )

        requireContext().registerReceiver(
            eventBusBroadcastReceiver,
            EventBusBroadCastReceiver.newIntentFilter()
        )
    }

    override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()

        EventBus.getDefault().unregister(this)
    }

    override fun onPause() {
        super.onPause()

        requireContext().unregisterReceiver(objectBroadcastReceiver)
        requireContext().unregisterReceiver(liveDataBroadCastReceiver)
        requireContext().unregisterReceiver(eventBusBroadcastReceiver)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTextEvent(event: TextEvent) {
        binding.messageEventBusMtv.text = event.text
    }

    companion object {
        val tag: String = ResultFragment::class.java.simpleName
        const val KEY_BROADCAST_TEXT = "KEY_BROADCAST_TEXT"
        const val OBJECT_BROADCAST_RECEIVER_ACTION = "OBJECT_BROADCAST_RECEIVER_ACTION"

        fun newObjectBroadcastIntent(text: String) = Intent().apply {
            action = OBJECT_BROADCAST_RECEIVER_ACTION
            putExtra(KEY_BROADCAST_TEXT, text)
        }

        fun newInstance() = ResultFragment()
    }
}