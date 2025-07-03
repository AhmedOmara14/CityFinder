package com.omaradev.cityfinder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omaradev.cityfinder.data.local.entity.CityEntity

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<CityEntity>)

    @Query("SELECT * FROM cities WHERE name LIKE :prefix || '%' ORDER BY name ASC")
    suspend fun searchCitiesByPrefix(prefix: String): List<CityEntity>

    @Query("SELECT * FROM cities ORDER BY name ASC")
    suspend fun getAllCitiesSorted(): List<CityEntity>
}
