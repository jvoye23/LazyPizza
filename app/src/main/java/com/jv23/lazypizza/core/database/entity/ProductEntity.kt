package com.jv23.lazypizza.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val ingredients: String?,
    val price: Double,
    val productCategory: String,
    val imageUrl: String,
    val toppingIds: String?
)