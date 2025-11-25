package com.jv23.lazypizza.core.domain.model

sealed class Product {
    abstract val id: String
    abstract val name: String
    abstract val price: Double
    abstract val productCategory: ProductCategory
    abstract val imageUrl: String
}

data class Pizza(
    override val id: String,
    override val name: String,
    val ingredients: String?,
    override val price: Double,
    override val productCategory: ProductCategory = ProductCategory.PIZZA,
    override val imageUrl: String,
    val toppingIds: String?
): Product()

data class Drink(
    override val id: String,
    override val name: String,
    override val price: Double,
    override val productCategory: ProductCategory = ProductCategory.DRINKS,
    override val imageUrl: String
): Product()

data class IceCream(
    override val id: String,
    override val name: String,
    override val price: Double,
    override val productCategory: ProductCategory = ProductCategory.ICE_CREAM,
    override val imageUrl: String
): Product()

data class Sauce(
    override val id: String,
    override val name: String,
    override val price: Double,
    override val productCategory: ProductCategory = ProductCategory.SAUCES,
    override val imageUrl: String
): Product()

data class Topping(
    override val id: String,
    override val name: String,
    override val price: Double,
    override val productCategory: ProductCategory = ProductCategory.PIZZA,
    override val imageUrl: String
): Product()

data class ProductCatalog(
    val pizzas: Map<String, Pizza>,
    val toppings: Map<String, Topping>,
    val drinks: Map<String, Drink>,
    val iceCreams: Map<String, IceCream>,
    val sauces: Map<String, Sauce>,
)


