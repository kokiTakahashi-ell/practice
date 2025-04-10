package com.example.busschedule.data.airport

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Update
    suspend fun updateAirport(airport: Airport)
    @Query(
        """
        SELECT * FROM airport 
        WHERE name LIKE '%' || :keyword || '%' 
        """
    )
    fun getSearch(keyword: String): Flow<List<Airport>>

    @Query(
        """
    SELECT * FROM airport 
    WHERE iata_code = :depart
    ORDER BY passengers DESC
    """
    )
    fun getByIataCode(depart: String): Flow<List<Airport>>
}