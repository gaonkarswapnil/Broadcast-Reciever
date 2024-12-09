package com.example.broadcastreciever.withroomandworker

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Time
import java.time.Instant
import java.time.LocalDateTime

class ContactWorker(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {
    val database = Room.databaseBuilder(
        applicationContext,
        ContactDatabase::class.java,
        "contactDB"
    ).build()
    private var flag: Boolean = false

    var list: List<Contact> = mutableListOf()

    override fun doWork(): Result {
        val data = readDataBase()
        if(data){
            Log.d("ContactDataBase", Result.success().toString())
            return Result.success()
        }else{
            Log.d("ContactDataBase", Result.failure().toString())
            return Result.failure()
        }
    }

    fun readDataBase(): Boolean{
        runBlocking {
            list = database.contactDao().getData()
        }

        for (contact in list){
            if(!contact.isSync){
                Log.d("ContactDataBase", contact.name)

                contact.time = LocalDateTime.now().toString()
                contact.isSync = true
                flag = true
                runBlocking{
                    database.contactDao().updateContact(contact)
                }

            }
        }
        return flag
    }
}