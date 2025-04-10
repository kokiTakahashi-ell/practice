package com.example.busschedule.data

import android.R.attr.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java

@Database(entities = [BusEntity::class], version = 1, exportSchema = false)
abstract class BusDatabase : RoomDatabase() {

    abstract fun busDao(): BusDao

    companion object {
        @Volatile
        private var Instance: com.example.busschedule.data.BusDatabase? = null

        fun getDatabase(context: Context): com.example.busschedule.data.BusDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, com.example.busschedule.data.BusDatabase::class.java, "app_schedule")
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}