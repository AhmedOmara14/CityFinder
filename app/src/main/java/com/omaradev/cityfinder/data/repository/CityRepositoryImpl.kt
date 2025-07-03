package com.omaradev.cityfinder.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.omaradev.cityfinder.data.local.CityDao
import com.omaradev.cityfinder.data.local.entity.CityEntity
import com.omaradev.cityfinder.data.mapper.toDomain
import com.omaradev.cityfinder.data.model.CityDto
import com.omaradev.cityfinder.domain.model.City
import com.omaradev.cityfinder.domain.repository.CityRepository
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.buffer
import okio.source
import java.io.IOException
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dao: CityDao,
    private val sharedPreferences: SharedPreferences
) : CityRepository {

    companion object {
        private const val PREFS_KEY_IS_LOADED = "is_cities_loaded"
    }

    override suspend fun searchCitiesByPrefix(prefix: String): List<City> {
        loadCitiesIfNeeded()
        return dao.searchCitiesByPrefix(prefix).map { it.toDomain() }
    }

    override suspend fun getAllCitiesSorted(): List<City> {
        loadCitiesIfNeeded()
        return dao.getAllCitiesSorted().map { it.toDomain() }
    }

    private suspend fun loadCitiesIfNeeded() = withContext(Dispatchers.IO) {
        val isLoaded = sharedPreferences.getBoolean(PREFS_KEY_IS_LOADED, false)
        if (isLoaded) return@withContext

        try {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val adapter = moshi.adapter(CityDto::class.java)

            val cities = mutableListOf<CityEntity>()

            context.assets.open("all_cities.json").source().buffer().use { bufferedSource ->
                val jsonReader = JsonReader.of(bufferedSource)
                jsonReader.beginArray()
                while (jsonReader.hasNext()) {
                    val cityDto = adapter.fromJson(jsonReader)
                    cityDto?.let {
                        cities.add(
                            CityEntity(
                                imageId = it.id,
                                name = it.name,
                                country = it.country,
                                lat = it.coord.lat,
                                lon = it.coord.lon
                            )
                        )
                    }
                }
                jsonReader.endArray()
            }

            dao.insertAll(cities)
            sharedPreferences.edit().putBoolean(PREFS_KEY_IS_LOADED, true).apply()

        } catch (e: Exception) {
            throw IOException("Failed to load cities data", e)
        }
    }
}
