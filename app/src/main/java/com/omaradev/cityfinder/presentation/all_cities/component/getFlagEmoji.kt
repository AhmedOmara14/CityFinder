package com.omaradev.cityfinder.presentation.all_cities.component

fun getFlagEmoji(countryCode: String): String {
    return countryCode.uppercase().map {
        Character.toChars(0x1F1E6 + (it.code - 'A'.code)).concatToString()
    }.joinToString("")
}
