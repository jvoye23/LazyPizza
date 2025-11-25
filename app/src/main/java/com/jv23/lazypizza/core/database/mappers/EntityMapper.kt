package com.jv23.lazypizza.core.database.mappers

import com.jv23.lazypizza.core.database.entity.ProductEntity
import com.jv23.lazypizza.core.domain.model.Drink
import com.jv23.lazypizza.core.domain.model.IceCream
import com.jv23.lazypizza.core.domain.model.Pizza
import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.domain.model.Sauce
import com.jv23.lazypizza.core.domain.model.Topping


fun ProductEntity.toPizza(): Pizza {
    return Pizza(
        id = id,
        name = name,
        ingredients = ingredients,
        price = price,
        productCategory = ProductCategory.PIZZA,
        imageUrl = imageUrl,
        toppingIds = toppingIds
    )
}

fun Pizza.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        ingredients = ingredients,
        price = price,
        productCategory = "PIZZA",
        imageUrl = imageUrl,
        toppingIds = toppingIds
    )
}

fun Drink.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        productCategory = "DRINK",
        imageUrl = imageUrl,
        ingredients = null,
        toppingIds = null
    )
}

fun IceCream.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        productCategory = "ICE CREAM",
        imageUrl = imageUrl,
        ingredients = null,
        toppingIds = null
    )
}

fun Sauce.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        productCategory = "SAUCE",
        imageUrl = imageUrl,
        ingredients = null,
        toppingIds = null
    )
}

fun Topping.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        productCategory = "TOPPING",
        imageUrl = imageUrl,
        ingredients = null,
        toppingIds = null
    )
}



fun ProductEntity.toTopping(): Topping {
    return Topping(
        id = id,
        name = name,
        price = price,
        productCategory = ProductCategory.PIZZA,
        imageUrl = imageUrl
    )
}

fun ProductEntity.toDrink(): Drink {
    return Drink(
        id = id,
        name = name,
        price = price,
        productCategory = ProductCategory.DRINKS,
        imageUrl = imageUrl,
    )
}

fun ProductEntity.toIceCream(): IceCream {
    return IceCream(
        id = id,
        name = name,
        price = price,
        productCategory = ProductCategory.ICE_CREAM,
        imageUrl = imageUrl,
    )
}

fun ProductEntity.toSauce(): Sauce {
    return Sauce(
        id = id,
        name = name,
        price = price,
        productCategory = ProductCategory.SAUCES,
        imageUrl = imageUrl
    )
}