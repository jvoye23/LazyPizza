package com.jv23.lazypizza.core.presentation.model

import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.domain.model.Topping


data class ProductUi(
    val id: String,
    val name: String,
    val ingredients: String?,
    val price: Int,
    val productCategory: ProductCategory,
    val imageUrl: String,
    val toppings: List<Topping>?
)

data class ToppingUi(
    val id: String,
    val name: String,
    val price: Int,
    val imageUrl: String
)