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
        val cityName = city.name.lowercase()
        for (char in cityName) {
            node = node.children.getOrPut(char) { CityNode() }
        }
        node.cities.add(city)
    }

    fun search(prefix: String): List<CityDto> {
        var node = root
        val cityName = prefix.lowercase()
        for (char in cityName) {
            node = node.children[char] ?: return emptyList()
        }
        val result = mutableListOf<CityDto>()
        collectCities(node, result)
        return result
    }

    fun getAllCities(): List<CityDto> {
        val result = mutableListOf<CityDto>()
        collectCities(root, result)
        return result
    }

    private fun collectCities(node: CityNode, result: MutableList<CityDto>) {
        result.addAll(node.cities)
        for (child in node.children.values) {
            collectCities(child, result)
        }
    }
}
