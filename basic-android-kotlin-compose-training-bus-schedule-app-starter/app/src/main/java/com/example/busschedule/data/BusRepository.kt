package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusRepository {
    fun getAllBusStream(): Flow<List<BusEntity>>
    fun getBusStream(stopName: String): Flow<List<BusEntity>>
    suspend fun insertBus(busEntity: BusEntity)
    suspend fun deleteBus(busEntity: BusEntity)
    suspend fun updateBus(busEntity: BusEntity)
}