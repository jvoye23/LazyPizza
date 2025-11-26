package com.jv23.lazypizza.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toppings")
data class ToppingEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String
)