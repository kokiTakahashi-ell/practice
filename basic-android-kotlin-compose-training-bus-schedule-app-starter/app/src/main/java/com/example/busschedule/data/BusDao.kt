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

    @Query("SELECT * from bus_schedule ORDER BY stopName ASC")
    fun getAllBus(): Flow<List<BusEntity>>

    @Query("SELECT * from bus_schedule WHERE id = :id")
    fun getBus(id: Int): Flow<BusEntity>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(busEntity: BusEntity)

    @Update
    suspend fun update(busEntity: BusEntity)

    @Delete
    suspend fun delete(busEntity: BusEntity)
}
