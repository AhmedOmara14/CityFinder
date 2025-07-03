package com.omaradev.cityfinder.presentation.all_cities.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CitySearchField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    placeholderText: String = "Search..."
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(color = Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(25.dp)),
        placeholder = { Text(placeholderText) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        singleLine = true,
        shape = RoundedCornerShape(25.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun CitySearchFieldPreview() {
    CitySearchField(
        searchQuery = "New York",
        onSearchQueryChanged = {}
    )
}
