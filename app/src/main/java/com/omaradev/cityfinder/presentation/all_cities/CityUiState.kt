package com.omaradev.cityfinder.presentation.all_cities

import com.omaradev.cityfinder.domain.model.City

data class CityUiState(
    val searchQuery: String = "",
    val groupedCities: Map<Char, List<City>> = emptyMap(),
    val isLoading: Boolean = true
)
