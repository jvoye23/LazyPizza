package com.jv23.lazypizza.core.presentation.model

import com.jv23.lazypizza.core.domain.model.ProductCategory


data class ProductUi(
    val id: String,
    val name: String,
    val ingredients: String?,
    val price: String,
    val productCategory: ProductCategory,
    val imageUrl: String,
    val toppingIds: List<String>?
)