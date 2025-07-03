package com.omaradev.cityfinder.data.mapper

import com.omaradev.cityfinder.data.local.entity.CityEntity
import com.omaradev.cityfinder.domain.model.City

fun CityEntity.toDomain(): City = City(
    id = imageId,
    name = name,
    country = country,
    latitude = lat,
    longitude = lon
)