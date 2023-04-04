package com.example.broadcastreceiver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .add(R.id.action_fcv, ActionFragment.newInstance(), ActionFragment.tag)
            .add(R.id.result_fcv, ResultFragment.newInstance(), ResultFragment.tag)
            .commit()

        setContentView(binding.root)
    }
}