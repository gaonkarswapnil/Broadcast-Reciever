package com.example.broadcastreciever.withroomandworker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactTable")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phoneNo: String,
    var isSync: Boolean = false,
    var time: String = ""
)