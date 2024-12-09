package com.example.broadcastreciever.withroomandworker

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.broadcastreciever.R
import com.example.broadcastreciever.databinding.ActivityContactBinding
import kotlinx.coroutines.launch

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private lateinit var database: ContactDatabase
    private var list: List<Contact> = mutableListOf()
    private lateinit var contactBroadcastReciever: ContactBroadcastReciever

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contactDB"
        ).build()

        val contact = Contact(name = "Tomas Barry",phoneNo = "(514) 339-6258")
        val contact1 = Contact(name = "Scott Wiggins",phoneNo = "(360) 369-6390")

        lifecycleScope.launch {
            database.contactDao().insertContact(contact)
            database.contactDao().insertContact(contact1)
        }


//        lifecycleScope.launch {
//            list = database.contactDao().getData()
//        }

//        binding.fbSync.setOnClickListener {
            contactBroadcastReciever = ContactBroadcastReciever()
            val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            registerReceiver(contactBroadcastReciever, filter)
//        }


    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(contactBroadcastReciever)
    }
}