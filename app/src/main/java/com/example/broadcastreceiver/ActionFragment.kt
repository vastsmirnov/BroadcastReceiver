package com.example.broadcastreceiver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

        binding.sendMb.setOnClickListener {
            hideKeyboard(it)
            val text = binding.messageEt.text.toString()
            requireContext().sendBroadcast(
                ResultFragment.newBroadcastIntent(text)
            )
        }

        binding.clearMb.setOnClickListener {
            binding.messageEt.setText("")
        }

        return binding.root
    }

    private fun hideKeyboard(view: View?) {
        if (view == null) return

        val inputMethodManager: InputMethodManager =
            requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }

    companion object {
        val tag: String = ActionFragment::class.java.simpleName

        fun newInstance() = ActionFragment()
    }
}