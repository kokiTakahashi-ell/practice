package com.example.busschedule.data

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

class OfflineBusRepository(private val busDao: BusDao) : BusRepository {
    override fun getAllBusStream(): Flow<List<BusEntity>> = busDao.getAllBus()

    override fun getBusStream(stopName: String): Flow<List<BusEntity>> = busDao.getBus(stopName = stopName)

    override suspend fun insertBus(bus: BusEntity) = busDao.insert(bus)

    override suspend fun deleteBus(bus: BusEntity) = busDao.delete(bus)

    override suspend fun updateBus(bus: BusEntity) = busDao.update(bus)
}