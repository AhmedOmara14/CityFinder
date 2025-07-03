package com.omaradev.cityfinder.presentation.all_cities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.omaradev.cityfinder.presentation.all_cities.component.CityGroupedList
import com.omaradev.cityfinder.presentation.all_cities.component.CityItem
import kotlin.collections.get


@OptIn(ExperimentalFoundationApi::class)
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
            .background(Color(0xFFF7F0F7)),
    ) {
        Text(
            text = "City Search",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "${uiState.groupedCities.values.flatten().size} cities",
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )

        CityGroupedList(
            groupedCities = uiState.groupedCities,
            isSectionExpanded = ::isExpanded,
            onToggleSection = ::toggleSection
        )
    }
}
