package com.example.busschedule.data.airport

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.busschedule.data.bus.AppDatabase
import com.example.busschedule.data.bus.BusSchedule
import com.example.busschedule.data.bus.BusScheduleDao
import kotlinx.coroutines.flow.Flow

@Database(entities = arrayOf(Airport::class), version = 1)
abstract class AirportDatabase: RoomDatabase() {
    abstract fun airportDao(): AirportDao

    companion object {
        @Volatile
        private var INSTANCE: AirportDatabase? = null

        fun getDatabase(context: Context): AirportDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AirportDatabase::class.java,
                    "app_database"
                )
//                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}