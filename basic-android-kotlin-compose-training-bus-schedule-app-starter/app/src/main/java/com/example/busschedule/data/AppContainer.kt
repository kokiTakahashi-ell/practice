package com.example.busschedule.data

import android.content.Context
import androidx.room.Database

interface AppContainer {
    val busRepository: BusRepository
}

class AppDataContainer(private val context: Context) : com.example.busschedule.data.AppContainer {
    override val busRepository: BusRepository by lazy {
        OfflineBusRepository(BusDatabase.getDatabase(context).busDao())
    }
}