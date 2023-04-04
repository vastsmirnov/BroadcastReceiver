package com.example.broadcastreceiver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.broadcastreceiver.databinding.FragmentActionBinding

class ActionFragment : Fragment() {
    lateinit var binding: FragmentActionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActionBinding.inflate(
            inflater,
            container,
            false
        )

        binding.clearMb.setOnClickListener {
            binding.messageEt.setText("")
        }

        return binding.root
    }

    companion object {
        val tag: String = ActionFragment::class.java.simpleName

        fun newInstance() = ActionFragment()
    }
}