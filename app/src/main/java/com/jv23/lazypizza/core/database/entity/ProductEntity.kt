package com.jv23.lazypizza.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.domain.model.Topping

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val ingredients: String?,
    val price: Double,
    val productCategory: ProductCategory,
    val imageUrl: String,
    val toppings: List<Topping>?
)