package com.example.broadcastreciever.dymanicbroadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class DynamicInternetReciever: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if(!isInternetAvailable(context)){
            showNoInternetDialog(context)
        }

    }

    private fun showNoInternetDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
            .setTitle("No Internet")
            .setPositiveButton("Ok"){dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()

        builder.show()
        Toast.makeText(context, "Seems you are offline", Toast.LENGTH_SHORT).show()
    }

    private fun isInternetAvailable(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capability = connectivityManager.getNetworkCapabilities(network)
        return capability != null && capability.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    }
}