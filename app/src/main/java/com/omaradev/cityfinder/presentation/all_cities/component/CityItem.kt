package com.omaradev.cityfinder.presentation.all_cities.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.omaradev.cityfinder.domain.model.City

@Composable
fun CityItem(city: City, navigateToMap: (Double, Double) -> Unit = { _, _ -> }) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (line, card) = createRefs()

        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .width(1.dp)
                .constrainAs(line) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    height = androidx.constraintlayout.compose.Dimension.fillToConstraints
                }
                .background(Color.Gray)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(card) {
                    start.linkTo(line.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .padding(top = 16.dp, start = 32.dp, end = 16.dp, bottom = 16.dp)
                .background(Color.White, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .clickable { navigateToMap(city.latitude, city.longitude) }
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color.LightGray.copy(alpha = 0.3f), CircleShape)
                    .padding(16.dp)
            ) {
                Text(
                    text = getFlagEmoji(city.country),
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "${city.name}, ${city.country}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = "${city.latitude}, ${city.longitude}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun CityItemShimmer() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (line, card) = createRefs()

        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .width(1.dp)
                .constrainAs(line) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    height = androidx.constraintlayout.compose.Dimension.fillToConstraints
                }
                .background(Color.Gray)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .constrainAs(card) {
                    start.linkTo(line.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .padding(top = 16.dp, start = 32.dp, end = 16.dp, bottom = 16.dp)
                .shimmerEffect(16.dp)
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {}
    }
}


@Preview
@Composable
private fun CityItemPreview() {
    CityItem(
        city = City(
            id = 90,
            name = "New York",
            country = "US",
            latitude = 40.7128,
            longitude = -74.0060
        )
    )
}

@Preview
@Composable
private fun CityItemShimmerPreview() {
    CityItemShimmer()
}