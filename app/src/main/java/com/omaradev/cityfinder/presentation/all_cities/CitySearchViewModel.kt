package com.omaradev.cityfinder.presentation.all_cities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.cityfinder.domain.usecase.GetAllCitiesUseCase
import com.omaradev.cityfinder.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitySearchViewModel @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val searchCityUseCase: SearchCityUseCase
) : ViewModel() {
    var uiState by mutableStateOf(CityUiState())
        private set

    init {
        loadAllCities()
    }

    private fun loadAllCities() {
        viewModelScope.launch {
            val results = getAllCitiesUseCase()
            val grouped = results.groupBy { it.name.first().uppercaseChar() }
            uiState = uiState.copy(groupedCities = grouped, isLoading = false)
        }
    }

    fun onSearchQueryChanged(query: String) {
        uiState = uiState.copy(searchQuery = query)

        viewModelScope.launch {
            val results = if (query.isBlank()) {
                getAllCitiesUseCase()
            } else {
                uiState = uiState.copy(isLoading = true)
                searchCityUseCase(query)
            }

            val grouped = results.groupBy { it.name.first().uppercaseChar() }
            uiState = uiState.copy(groupedCities = grouped, isLoading = false)
        }
    }



}
