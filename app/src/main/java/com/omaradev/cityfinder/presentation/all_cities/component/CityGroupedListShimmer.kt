package com.omaradev.cityfinder.presentation.all_cities.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CityGroupedListShimmer(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        listOf<String>("A","B","C").forEach {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        if (it != "A") {
                            VerticalLine()
                        }

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .shimmerEffect(50.dp)
                                .border(1.dp, Color.Gray, CircleShape)
                                .clip(CircleShape),
                            contentAlignment = Alignment.Center
                        ) {}

                        VerticalLine()
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                }
            }

            items(2) {
                CityItemShimmer()
            }
        }
    }
}

@Preview
@Composable
private fun CityGroupedListShimmerPreview() {
    CityGroupedListShimmer()
}