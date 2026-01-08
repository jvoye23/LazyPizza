package com.jv23.lazypizza.core.presentation.mapper

import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.Topping
import com.jv23.lazypizza.core.presentation.designsystem.util.toCurrencyString
import com.jv23.lazypizza.core.presentation.model.ProductUi
import com.jv23.lazypizza.core.presentation.model.ToppingUi
import java.text.NumberFormat
import java.util.Locale

fun Topping.toToppingUi(): ToppingUi {
    val displayPrice = NumberFormat.getCurrencyInstance(Locale.US).format(price)

    return ToppingUi(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl
    )
}

fun Product.toProductUi(): ProductUi {
    val displayPrice = NumberFormat.getCurrencyInstance(Locale.US).format(price)

    return ProductUi(
        id = id,
        name = name,
        ingredients = ingredients,
        price = price,
        productCategory = productCategory,
        imageUrl = imageUrl,
        toppings = toppings
    )
}