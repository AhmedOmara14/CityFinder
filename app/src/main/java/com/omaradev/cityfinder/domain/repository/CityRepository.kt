package com.omaradev.cityfinder.domain.repository

import com.omaradev.cityfinder.domain.model.City

interface CityRepository {
    suspend fun getAllCitiesSorted(): List<City>
    suspend fun searchCitiesByPrefix(prefix: String): List<City>
}

