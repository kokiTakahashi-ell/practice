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

import android.content.Context
import android.database.sqlite.SQLiteDatabase.deleteDatabase
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Dao
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.airport.Airport
import com.example.busschedule.data.airport.AirportDao
import com.example.busschedule.data.bus.BusSchedule
import com.example.busschedule.data.bus.BusScheduleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class BusScheduleViewModel(private val busScheduleDao: BusScheduleDao, private val airportDao: AirportDao): ViewModel() {

    private val _searchUiState = MutableStateFlow(DefaultState())
    val searchUiState: StateFlow<DefaultState> = _searchUiState.asStateFlow()


    //入力状態のテキストのUiStateを更新する
    fun updateSearchText(text: String) {
        _searchUiState.value = _searchUiState.value.copy(textSearch = text)
    }
    //選択した出発地の空港のUiStateを更新する
    fun updateSelectedAirport(airport: Airport) {
        _searchUiState.value = _searchUiState.value.copy(selectedAirport = airport)
    }
    //選択された出発地以外の空港リストのUiStateを更新する
    fun updateListAirport(airports: Airport) {
        viewModelScope.launch {
            val updateList = getExcludeAirport(airports.iataCode).first()
            _searchUiState.value = _searchUiState.value.copy(listAirport = updateList)
        }
    }

    fun updateFavoriteAirport() {
        viewModelScope.launch {
            // favoriteの全てのリスト取得
            val schedules = getFullSchedule().first()

            // 空港情報を格納するリスト
            val departureAirports = mutableListOf<Airport>()
            val destinationAirports = mutableListOf<Airport>()

            // 各favoriteのdepartureとdestinationを参照して空港情報を取得
            schedules.forEach { schedule ->
                val departureAirport = getIataAirport(schedule.departureCode).firstOrNull()
                val destinationAirport = getIataAirport(schedule.destinationCode).firstOrNull()

                // 取得した空港情報をリストに追加
                if (departureAirport != null) {
                    departureAirports.add(departureAirport)
                }
                if (destinationAirport != null) {
                    destinationAirports.add(destinationAirport)
                }
            }

            // Stateを更新
            _searchUiState.value = _searchUiState.value.copy(
                favoriteDepartureAirport = departureAirports,
                favoriteDestinationAirport = destinationAirports
            )
        }
    }
    suspend fun insertFavoriteAirport(departure: String, destination: String) {
        Log.d(TAG, "BusScheduleDao: $busScheduleDao")
        try {
            busScheduleDao.insert(busSchedule = BusSchedule(departureCode = departure, destinationCode = destination))
            Log.d(TAG, "insertFavoriteAirport: $departure, $destination")
        } catch (
            e: Exception) {
            Log.e("BusScheduleViewModel", "Error inserting favorite airport", e)
        }
//        busScheduleDao.insert(busSchedule = BusSchedule(departureCode = departure, destinationCode = destination))
//        Log.d(TAG, "insertFavoriteAirport: $departure, $destination")
    }

    //favorite
    fun getFullSchedule(): Flow<List<BusSchedule>> = busScheduleDao.getAll()
    fun existsByFavorite(departure: String, destination: String): Flow<Boolean> =
        busScheduleDao.existsByDepartureAndDestination(departure = departure, destination = destination)
//    suspend fun insertSchedule(busSchedule: BusSchedule) = busScheduleDao.insert(busSchedule)
//    suspend fun deleteSchedule(busSchedule: BusSchedule) = busScheduleDao.delete(busSchedule)
//    suspend fun updateSchedule(busSchedule: BusSchedule) = busScheduleDao.update(busSchedule)

    fun deleteDatabase(context: Context, databaseName: String) {
        val deleted = context.deleteDatabase(databaseName)
        if (deleted) {
            Log.d("Database", "Database $databaseName deleted successfully.")
        } else {
            Log.e("Database", "Failed to delete database $databaseName.")
        }
    }

    //Airport
    fun getSearchAirports(keyword: String): Flow<List<Airport>> = airportDao.getSearch(keyword = keyword)
    fun getIataAirport(iata: String): Flow<Airport> = airportDao.getByIataCode(depart = iata)
    fun getExcludeAirport(iata: String): Flow<List<Airport>> = airportDao.getAirportsExcluding(iataCode = iata)

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BusScheduleApplication)
                BusScheduleViewModel(application.database.busScheduleDao(), application.airportDatabase.airportDao())
            }
        }
    }
}

data class DefaultState(
    val textSearch: String = "",
    val selectedAirport: Airport? = null, // 選択された空港
    val listAirport: List<Airport> = emptyList(), // 空港リスト
    val favoriteDepartureAirport: List<Airport> = emptyList(), // 空港リスト
    val favoriteDestinationAirport: List<Airport> = emptyList(),
)


