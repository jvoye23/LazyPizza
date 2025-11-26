package com.jv23.lazypizza.core.domain.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val productCategory: ProductCategory,
    val imageUrl: String,
    val ingredients: String?,
    val toppings: List<Topping>?,
)

data class Topping(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String
)

data class ProductCatalog(
    val products: Map<String, Product>,
    val toppings: Map<String, Topping>
)


