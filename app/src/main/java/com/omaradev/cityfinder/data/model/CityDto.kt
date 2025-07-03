package com.omaradev.cityfinder.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityDto(
    @Json(name = "_id") val id: Long,
    val name: String,
    val country: String,
    val coord: CoordDto
)

@JsonClass(generateAdapter = true)
data class CoordDto(
    val lon: Double,
    val lat: Double
)
