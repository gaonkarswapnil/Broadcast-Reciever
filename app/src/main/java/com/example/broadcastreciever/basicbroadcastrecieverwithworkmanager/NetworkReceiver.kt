package com.example.broadcastreciever.basicbroadcastrecieverwithworkmanager

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
import androidx.work.WorkManager

class NetworkReceiver: BroadcastReceiver() {
    private fun isInternetOn(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if(!isInternetOn(context)){
            showDialog(context)
        }else{
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val workerRequest = OneTimeWorkRequest.Builder(NetworkWorker::class.java)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueue(workerRequest)
        }
    }

    private fun showDialog(context: Context) {

        if (context is Activity) {
            val builder = AlertDialog.Builder(context)
                .setTitle("NO Internet")
                .setMessage("Plz switch on your internet")
                .setCancelable(false)
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                    context.finish()
                }
                .create()

            builder.show()
        }
    }
}