package com.omaradev.cityfinder.domain.usecase

import android.util.Log
import com.omaradev.cityfinder.domain.model.City
import com.omaradev.cityfinder.domain.repository.CityRepository
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(): List<City> {
        val citites = cityRepository.getAllCitiesSorted().toMutableList()
        Log.d("TAG", "invoke: "+citites)
        return cityRepository.getAllCitiesSorted()
    }
}
