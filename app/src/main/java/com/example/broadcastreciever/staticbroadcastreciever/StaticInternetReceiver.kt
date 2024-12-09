package com.example.broadcastreciever.staticbroadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class StaticInternetReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, p1: Intent?) {
        if(!isInternetAvailable(context)){
            val dialogIntent = Intent(context, StaticBroadcastRecieverActivity::class.java)
            dialogIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(dialogIntent)
        }
    }

    fun isInternetAvailable(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}