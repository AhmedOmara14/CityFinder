package com.omaradev.cityfinder.presentation.all_cities

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.omaradev.cityfinder.presentation.all_cities.component.CityGroupedList
import com.omaradev.cityfinder.presentation.all_cities.component.CityGroupedListShimmer
import com.omaradev.cityfinder.presentation.all_cities.component.CitySearchField

@Composable
fun AllCitiesScreen(
    modifier: Modifier,
    viewModel: CitySearchViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val expandedSections = remember { mutableStateMapOf<Char, Boolean>() }

    fun isExpanded(letter: Char): Boolean {
        return expandedSections[letter] ?: true
    }

    fun toggleSection(letter: Char) {
        expandedSections[letter] = !(expandedSections[letter] ?: true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF7F0F7))
    ) {
        Text(
            text = "City Search",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "${uiState.groupedCities.values.flatten().size} cities",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )

        Crossfade(
            targetState = uiState.isLoading,
            modifier = Modifier.weight(1f)
        ) { isLoading ->
            if (isLoading) {
                CityGroupedListShimmer(
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                CityGroupedList(
                    modifier = Modifier.fillMaxSize(),
                    groupedCities = uiState.groupedCities,
                    isSectionExpanded = ::isExpanded,
                    onToggleSection = ::toggleSection
                )
            }
        }

        CitySearchField(
            searchQuery = uiState.searchQuery,
            onSearchQueryChanged = viewModel::onSearchQueryChanged
        )
    }
}

