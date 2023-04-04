package com.example.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.broadcastreceiver.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val broadcastReceiver = MyReceiver()
    private lateinit var intentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intentFilter = IntentFilter("com.example.broadcastreceiver.SEND_MESSAGE")
    }

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
        requireContext().registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(broadcastReceiver)
    }

    companion object {
        val tag = ResultFragment::class.java.simpleName
        const val KEY_BROADCAST_TEXT = "KEY_BROADCAST_TEXT"

        fun newBroadcastIntent(text: String) = Intent().apply {
            action = "com.example.broadcastreceiver.SEND_MESSAGE"
            putExtra(KEY_BROADCAST_TEXT, text)
        }

        fun newInstance() = ResultFragment()
    }
}