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
package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.airport.Airport
import com.example.busschedule.data.airport.AirportDao
import com.example.busschedule.data.bus.BusSchedule
import com.example.busschedule.data.bus.BusScheduleDao
import kotlinx.coroutines.flow.Flow


class BusScheduleViewModel(private val busScheduleDao: BusScheduleDao, private val airportDao: AirportDao): ViewModel() {
    //    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> =
    //        busScheduleDao.getByStopName(stopName)


    //favorite
    fun getFullSchedule(): Flow<List<BusSchedule>> = busScheduleDao.getAll()
    suspend fun insertSchedule(busSchedule: BusSchedule) = busScheduleDao.insert(busSchedule)
    suspend fun deleteSchedule(busSchedule: BusSchedule) = busScheduleDao.delete(busSchedule)
    suspend fun updateSchedule(busSchedule: BusSchedule) = busScheduleDao.update(busSchedule)

    //Airport
    fun getAllAirports(keyword: String): Flow<List<Airport>> = airportDao.getSearch(keyword = keyword)
    fun getIataAirport(departure: String): Flow<List<Airport>> = airportDao.getByIataCode(depart = departure)

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BusScheduleApplication)
                BusScheduleViewModel(application.database.busScheduleDao(), application.airportDatabase.airportDao())
            }
        }
    }
}
