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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.data.BusEntity
import com.example.busschedule.data.BusRepository
import com.example.busschedule.data.OfflineBusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BusScheduleViewModel(private val busRepository: BusRepository): ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        busRepository.getAllBusStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    var busUiState by mutableStateOf(BusUiState())
        private set

    // Get example bus schedule
//    fun getFullSchedule(): Flow<List<BusEntity>> = flowOf(
//        listOf(
//            BusEntity(
//                1,
//                "Example Street",
//                0
//            )
//        )
//    )

    // Get example bus schedule by stop
//    fun getScheduleFor(stopName: String): Flow<List<BusEntity>> = flowOf(
//        listOf(
//            BusEntity(
//                1,
//                "Example Street",
//                0
//            )
//        )
//    )

    fun getFullSchedule(): Flow<List<BusEntity>> {
        return busRepository.getAllBusStream()
    }

    // バス停名でスケジュールを取得
    fun getScheduleFor(stopName: String): Flow<List<BusEntity>> {
        return busRepository.getBusStream(stopName)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


data class BusUiState(
    val busDetails: BusDetails = BusDetails(),
    val isEntryValid: Boolean = false
)

data class BusDetails(
    val id: Int = 0,
    val stopName: String = "",
    val arrivalTimeInMillis: String = "",
)

fun BusDetails.toBus(): BusEntity = BusEntity(
    id = id,
    stopName = stopName,
    arrivalTimeInMillis = arrivalTimeInMillis.toLongOrNull() ?: 0
)

fun BusEntity.formattedArrivalTime(): String {
    return SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date(arrivalTimeInMillis * 1000))
}

/**
 * Extension function to convert [Bus] to [BusUiState]
 */
fun BusEntity.toBusUiState(isEntryValid: Boolean = false): BusUiState = BusUiState(
    busDetails = this.toBusDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Bus] to [BusDetails]
 */
fun BusEntity.toBusDetails(): BusDetails = BusDetails(
    id = id,
    stopName = stopName,
    arrivalTimeInMillis = arrivalTimeInMillis.toString(),
)

data class BusDetailsUiState(
    val outOfService: Boolean = true,
    val busDetails: BusDetails = BusDetails()
)

data class HomeUiState(val itemList: List<BusEntity> = listOf())