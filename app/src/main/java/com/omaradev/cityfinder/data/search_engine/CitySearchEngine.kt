package com.omaradev.cityfinder.data.search_engine

import com.omaradev.cityfinder.data.model.CityDto

class CityNode {
    val children = mutableMapOf<Char, CityNode>()
    val cities = mutableListOf<CityDto>()
}

class CitySearchEngine {
    private val root = CityNode()

    fun insert(city: CityDto) {
        var node = root
        val word = city.name.lowercase()
        for (char in word) {
            node = node.children.getOrPut(char) { CityNode() }
        }
        node.cities.add(city)
    }

    fun search(prefix: String): List<CityDto> {
        var node = root
        val word = prefix.lowercase()
        for (char in word) {
            node = node.children[char] ?: return emptyList()
        }
        return collectCities(node)
    }

    private fun collectCities(node: CityNode): List<CityDto> {
        val result = mutableListOf<CityDto>()
        result.addAll(node.cities)
        for (child in node.children.values) {
            result.addAll(collectCities(child))
        }
        return result
    }
}