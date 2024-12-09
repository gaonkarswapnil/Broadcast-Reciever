package com.example.broadcastreciever.basicbroadcastrecieverwithworkmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class NetworkWorker(context: Context, workerParameter: WorkerParameters): Worker(context, workerParameter) {
    override fun doWork(): Result {
        try {
            Log.d("NetworkWorker", "Network Worker called when the Internet in ON")
            return Result.success()
        }catch (e: Exception){
            e.printStackTrace()
            return Result.failure()
        }
    }

}