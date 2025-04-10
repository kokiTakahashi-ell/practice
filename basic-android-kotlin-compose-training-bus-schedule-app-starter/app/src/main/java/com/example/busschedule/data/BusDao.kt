package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusDao {

    @Query("SELECT * from Schedule ORDER BY arrival_time ASC")
    fun getAllBus(): Flow<List<BusEntity>>

    @Query("SELECT * from Schedule WHERE stop_name = :stopName")
    fun getBus(stopName: String): Flow<List<BusEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(busEntity: BusEntity)

    @Update
    suspend fun update(busEntity: BusEntity)

    @Delete
    suspend fun delete(busEntity: BusEntity)
}
