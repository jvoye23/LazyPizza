package com.jv23.lazypizza.core.database.mappers

import com.jv23.lazypizza.core.database.entity.ProductEntity
import com.jv23.lazypizza.core.database.entity.ToppingEntity
import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.Topping

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        productCategory = productCategory,
        imageUrl = imageUrl,
        ingredients = ingredients,
        toppings = toppings
    )
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        productCategory = productCategory,
        imageUrl = imageUrl,
        ingredients = ingredients,
        toppings = toppings
    )
}

fun Topping.toToppingEntity(): ToppingEntity {
    return ToppingEntity(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl
    )
}

fun ToppingEntity.toTopping(): Topping {
    return Topping(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl
    )
}