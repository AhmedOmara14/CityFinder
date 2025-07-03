package com.omaradev.cityfinder.presentation.all_cities.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omaradev.cityfinder.domain.model.City
import com.omaradev.cityfinder.utils.navigateToMapWithLatAndLng

@Composable
fun CityGroupedList(
    modifier: Modifier = Modifier,
    groupedCities: Map<Char, List<City>>,
    isSectionExpanded: (Char) -> Boolean,
    onToggleSection: (Char) -> Unit
) {
    val sortedGroups = groupedCities.toSortedMap()
    val context = LocalContext.current

    if (sortedGroups.isNotEmpty()) {
        val firstLetter = sortedGroups.firstKey()

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            sortedGroups.forEach { (letter, cities) ->
                item {
                    CircleLetterSection(
                        firstLetter = firstLetter,
                        currentLetter = letter,
                        onClick = { onToggleSection(letter) }
                    )
                }

                if (isSectionExpanded(letter)) {
                    items(cities) { city ->
                        CityItem(city) { latitude,longitude ->
                            navigateToMapWithLatAndLng(
                                context = context,
                                latitude = latitude,
                                longitude = longitude
                            )
                        }
                    }
                }

                if (letter == sortedGroups.lastKey()) {
                    item {
                        LastCircleLetterSection()
                    }
                }
            }
        }
    }
}

@Composable
fun CircleLetterSection(
    firstLetter: Char,
    currentLetter: Char,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            if (currentLetter != firstLetter) {
                VerticalLine()
            }

            CircleLetter(letter = currentLetter, onClick = onClick)

            VerticalLine()
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun VerticalLine() {
    Box(
        modifier = Modifier
            .padding(start = 20.dp)
            .width(1.dp)
            .height(12.dp)
            .background(Color.Gray)
    )
}

@Composable
fun CircleLetter(letter: Char, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.White, CircleShape)
            .border(1.dp, Color.Gray, CircleShape)
            .clip(CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LastCircleLetterSection() {
    Column {
        VerticalLine()
        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(20.dp)
                .background(Color.Gray, CircleShape)
                .border(1.dp, Color.Gray, CircleShape),
            contentAlignment = Alignment.Center
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCityGroupedList() {
    val sampleCities = listOf(
        City(1, "Aabenraa", "DK", 55.044338, 9.41741),
        City(2, "Aalborg", "DK", 57.048, 9.9187),
        City(3, "Zaamslag", "NL", 51.3125, 3.9125)
    )

    val groupedCities = sampleCities.groupBy { it.name.first() }

    val expandedSections = remember {
        mutableStateMapOf<Char, Boolean>().apply {
            groupedCities.keys.forEach { put(it, true) }
        }
    }

    CityGroupedList(
        groupedCities = groupedCities,
        isSectionExpanded = { expandedSections[it] ?: true },
        onToggleSection = { key ->
            expandedSections[key] = !(expandedSections[key] ?: true)
        }
    )
}
