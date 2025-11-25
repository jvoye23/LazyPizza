package com.jv23.lazypizza.core.data.networking.dto

import kotlinx.serialization.Serializable

// This class will hold the entire product catalog from the root of the firebase realtime database
@Serializable
data class ProductCatalogDto(
    var pizzas: Map<String, PizzaDto> = emptyMap(),
    var sauces: Map<String, SaucesDto> = emptyMap(),
    var toppings: Map<String, ToppingsDto> = emptyMap(),
    var drinks: Map<String, DrinkDto> = emptyMap(),
    var iceCreams: Map<String, IceCreamDto> = emptyMap(),

)


@Serializable
data class PizzaDto(
    var id: String = "",
    var name: String = "",
    var ingredients: String = "",
    var price: Double = 0.0,
    var productCategory: String = "",
    var imageUrl: String = "",
    var toppingIds: List<Int> = emptyList()
)

@Serializable
data class DrinkDto(
    var id: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var productCategory: String = "",
    var imageUrl: String = ""
)

@Serializable
data class IceCreamDto(
    var id: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var productCategory: String = "",
    var imageUrl: String = ""
)

@Serializable
data class SaucesDto(
    var id: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var productCategory: String = "",
    var imageUrl: String = ""
)

@Serializable
data class ToppingsDto(
    var id: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var productCategory: String = "",
    var imageUrl: String = ""
)
