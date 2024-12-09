package com.example.broadcastreciever.staticbroadcastreciever

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.broadcastreciever.R
import com.example.broadcastreciever.databinding.ActivityStaticBroadcastRecieverBinding

class StaticBroadcastRecieverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStaticBroadcastRecieverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStaticBroadcastRecieverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val builder = AlertDialog.Builder(this)
            .setTitle("No Internet")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .create()

        builder.show()
    }
}