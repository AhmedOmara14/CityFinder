package com.omaradev.cityfinder.data.repository

import android.content.Context
import com.omaradev.cityfinder.data.mapper.toDomain
import com.omaradev.cityfinder.data.model.CityDto
import com.omaradev.cityfinder.data.search_engine.CitySearchEngine
import com.omaradev.cityfinder.domain.model.City
import com.omaradev.cityfinder.domain.repository.CityRepository
import kotlinx.serialization.json.Json

class CityRepositoryImpl(context: Context) : CityRepository {
    private val searchEngine: CitySearchEngine = CitySearchEngine()

    init {
        val jsonString =
            context.assets.open("all_cities.json").bufferedReader().use { it.readText() }
        val cityList: List<CityDto> = Json.decodeFromString(jsonString)
        cityList.forEach { searchEngine.insert(it) }
    }

    override suspend fun searchCitiesByPrefix(prefix: String): List<City> {
        return searchEngine.search(prefix).map { it.toDomain() }.sortedBy { it.name }
    }

    override suspend fun getAllCitiesSorted(): List<City> {
        return searchEngine.getAllCities().map { it.toDomain() }.sortedBy { it.name }
    }
}