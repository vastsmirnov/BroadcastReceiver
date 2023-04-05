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

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var objectBroadcastReceiver: BroadcastReceiver

    private val liveDataBroadCastReceiver = LiveDataBroadcastReceiver()
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
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        liveDataBroadCastReceiver.textLiveData.observe(viewLifecycleOwner) {
            binding.messageByLifeDataMtv.text = it
        }

        objectBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                binding.messageByObjectMtv.text = intent.getStringExtra(KEY_BROADCAST_TEXT)
            }
        }

        requireContext().registerReceiver(objectBroadcastReceiver, objectIntentFilter)
        requireContext().registerReceiver(
            liveDataBroadCastReceiver,
            LiveDataBroadcastReceiver.newIntentFilter()
        )
    }

    override fun onPause() {
        super.onPause()

        requireContext().unregisterReceiver(objectBroadcastReceiver)
        requireContext().unregisterReceiver(liveDataBroadCastReceiver)
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