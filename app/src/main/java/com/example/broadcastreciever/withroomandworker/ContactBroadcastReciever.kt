package com.example.broadcastreciever.withroomandworker

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AlertDialog
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class ContactBroadcastReciever: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if(!isInternetOn(context)){
            showDialog(context)
        }else{
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<ContactWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }

    private fun showDialog(context: Context) {
        if(context is Activity){
            val builder = AlertDialog.Builder(context)
                .setTitle("You are Offline")
                .setMessage("Turn on your Internet")
                .setCancelable(false)
                .setPositiveButton("Yes"){dialog, _ ->
                    dialog.dismiss()
                    context.finish()
                }
                .create()

            builder.show()
        }
    }

    private fun isInternetOn(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}