package com.omaradev.cityfinder.presentation.all_cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.cityfinder.domain.model.City
import com.omaradev.cityfinder.domain.usecase.GetAllCitiesUseCase
import com.omaradev.cityfinder.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitySearchViewModel @Inject constructor(
    private val searchCitiesByPrefixUseCase: SearchCityUseCase,
    private val getAllCitiesUseCase: GetAllCitiesUseCase
) : ViewModel() {

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()

    init {
        loadAllCities()
    }

    fun onSearch(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                loadAllCities()
            } else {
                _cities.value = searchCitiesByPrefixUseCase(query)
            }
        }
    }

    private fun loadAllCities() {
        viewModelScope.launch {
            _cities.value = getAllCitiesUseCase()
        }
    }
}