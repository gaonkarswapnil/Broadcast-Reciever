package com.example.broadcastreciever.dymanicbroadcastreciever

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.broadcastreciever.R
import com.example.broadcastreciever.databinding.ActivityDynamicBroadcastRecieverBinding

class DynamicBroadcastRecieverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDynamicBroadcastRecieverBinding
    private lateinit var internetReceiver: DynamicInternetReciever
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDynamicBroadcastRecieverBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        internetReceiver = DynamicInternetReciever()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(internetReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(internetReceiver)
    }
}