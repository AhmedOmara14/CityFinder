package com.omaradev.cityfinder.data.mapper

import com.omaradev.cityfinder.data.model.CityDto
import com.omaradev.cityfinder.domain.model.City

fun CityDto.toDomain(): City {
    return City(
        id = id,
        name = name,
        country = country,
        latitude = coord.lat,
        longitude = coord.lon
    )
}