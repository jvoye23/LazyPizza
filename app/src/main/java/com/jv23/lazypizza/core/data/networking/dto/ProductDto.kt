package com.jv23.lazypizza.core.data.networking.dto

import com.jv23.lazypizza.core.domain.model.ProductCategory
import kotlinx.serialization.Serializable

// This class will hold the entire product catalog from the root of the firebase realtime database
@Serializable
data class ProductCatalogDto(
    var products: Map<String, ProductDto> = emptyMap(),
    var toppings: Map<String, ToppingDto> = emptyMap(),
)
@Serializable
data class ProductDto(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val productCategory: ProductCategory = ProductCategory.PIZZA,
    val imageUrl: String = "",
    val ingredients: String? = null,
    val toppings: List<ToppingDto>? = null
)

@Serializable
data class ToppingDto(
    var id: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var imageUrl: String = ""
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
