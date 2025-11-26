package com.jv23.lazypizza.core.database.mappers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.domain.model.Topping

class RoomConverters {

    private val gson = Gson()


    @TypeConverter
    fun fromProductCategoryToString(category: ProductCategory): String {
        return category.name
    }

    @TypeConverter
    fun fromStringToProductCategory(value: String): ProductCategory {
        return try {
            ProductCategory.valueOf(value)
        } catch (e: IllegalArgumentException) {
            // Fallback strategy: If the DB contains a string not found in the Enum
            // (e.g., due to an app update removing a category), return a default.
            ProductCategory.PIZZA
        }
    }

    @TypeConverter
    fun fromToppingsListToJsonString(toppings: List<Topping>?): String? {
        if(toppings == null) {
            return null
        }
        return gson.toJson(toppings)
    }

    @TypeConverter
    fun fromJsonStringToToppingsList(json: String?): List<Topping>? {
        if(json.isNullOrBlank()) {
            return null
        }
        val type = object : TypeToken<List<Topping>>() {}.type
        return gson.fromJson(json, type)
    }
}