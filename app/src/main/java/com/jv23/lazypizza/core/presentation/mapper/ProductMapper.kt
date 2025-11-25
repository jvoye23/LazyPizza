package com.jv23.lazypizza.core.presentation.mapper

import com.jv23.lazypizza.core.domain.model.Drink
import com.jv23.lazypizza.core.domain.model.IceCream
import com.jv23.lazypizza.core.domain.model.Pizza
import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.Sauce
import com.jv23.lazypizza.core.domain.model.Topping
import com.jv23.lazypizza.core.presentation.model.ProductUi

fun Pizza.toProductUi(): ProductUi {
    val displayPrice = (price / 100.0).toString()
    displayPrice.format("%.2f", displayPrice)

    return ProductUi(
        id = id,
        name = name,
        ingredients = ingredients,
        price = displayPrice,
        productCategory = productCategory,
        imageUrl = imageUrl,
        toppingIds = emptyList()
    )
}

fun Drink.toProductUi(): ProductUi {
    val displayPrice = (price / 100.0).toString()
    displayPrice.format("%.2f", displayPrice)

    return ProductUi(
        id = id,
        name = name,
        ingredients = null,
        price = displayPrice,
        productCategory = productCategory,
        imageUrl = imageUrl,
        toppingIds = null
    )
}

fun IceCream.toProductUi(): ProductUi {
    val displayPrice = (price / 100.0).toString()
    displayPrice.format("%.2f", displayPrice)

    return ProductUi(
        id = id,
        name = name,
        ingredients = null,
        price = displayPrice,
        productCategory = productCategory,
        imageUrl = imageUrl,
        toppingIds = null
    )
}

fun Sauce.toProductUi(): ProductUi {
    val displayPrice = (price / 100.0).toString()
    displayPrice.format("%.2f", displayPrice)

    return ProductUi(
        id = id,
        name = name,
        ingredients = null,
        price = displayPrice,
        productCategory = productCategory,
        imageUrl = imageUrl,
        toppingIds = null
    )
}

fun Topping.toProductUi(): ProductUi {
    val displayPrice = (price / 100.0).toString()
    displayPrice.format("%.2f", displayPrice)

    return ProductUi(
        id = id,
        name = name,
        ingredients = null,
        price = displayPrice,
        productCategory = productCategory,
        imageUrl = imageUrl,
        toppingIds = null
    )
}

fun Product.toProductUi(): ProductUi {
    val displayPrice = (price / 100.0).toString()
    displayPrice.format("%.2f", displayPrice)

    return ProductUi(
        id = id,
        name = name,
        ingredients = null,
        price = displayPrice,
        productCategory = productCategory,
        imageUrl = imageUrl,
        toppingIds = null
    )
}