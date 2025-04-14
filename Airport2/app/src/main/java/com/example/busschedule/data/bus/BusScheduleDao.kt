/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule.data.bus

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Insert
    suspend fun insert(busSchedule: BusSchedule)
    @Delete
    suspend fun delete(busSchedule: BusSchedule)
    @Update
    suspend fun update(busSchedule: BusSchedule)

    @Query(
        """
        SELECT * FROM favorite    
        """
    )
    fun getAll(): Flow<List<BusSchedule>>

    @Query(
        """
        SELECT EXISTS(
            SELECT 1 FROM favorite 
            WHERE departure_code = :departure AND destination_code = :destination
        )
        """
    )
    fun existsByDepartureAndDestination(departure: String, destination: String): Flow<Boolean>
}
