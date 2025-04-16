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
    WHERE iata_code != :iataCode
    """
    )
    fun getAirportsExcluding(iataCode: String): Flow<List<Airport>>
    @Query(
        """
        SELECT * FROM airport 
        WHERE name LIKE '%' || :keyword || '%' ORDER BY passengers DESC
        """
    )
    fun getSearch(keyword: String): Flow<List<Airport>>

    @Query(
        """
    SELECT * FROM airport 
    WHERE iata_code = :depart
    """
    )
    fun getByIataCode(depart: String): Flow<Airport>
}