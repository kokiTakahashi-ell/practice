package com.example.busschedule.data

import android.R.attr.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java

@Database(entities = [BusEntity::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun BusDao(): BusDao

    companion object {
        @Volatile
        private var Instance: com.example.busschedule.data.InventoryDatabase? = null

        fun getDatabase(context: Context): com.example.busschedule.data.InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, com.example.busschedule.data.InventoryDatabase::class.java, "bus_schedule")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}