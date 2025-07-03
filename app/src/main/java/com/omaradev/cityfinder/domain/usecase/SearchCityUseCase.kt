package com.omaradev.cityfinder.domain.usecase

import com.omaradev.cityfinder.domain.model.City
import com.omaradev.cityfinder.domain.repository.CityRepository
import javax.inject.Inject

class SearchCityUseCase constructor(
    private val cityRepository: CityRepository
){
    suspend operator fun invoke(prefix: String): List<City> {
        return cityRepository.searchCitiesByPrefix(prefix)
    }
}
