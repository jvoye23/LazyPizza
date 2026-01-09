package com.jv23.lazypizza.core.presentation.mapper

import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.Topping
import com.jv23.lazypizza.core.presentation.model.ProductUi
import com.jv23.lazypizza.core.presentation.model.ToppingUi
import com.jv23.lazypizza.order_menu.presentation.product_detail.PizzaTopping

fun Topping.toToppingUi(): ToppingUi {
    return ToppingUi(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl
    )
}

fun Product.toProductUi(): ProductUi {
    return ProductUi(
        id = id,
        name = name,
        ingredients = ingredients,
        price = price,
        productCategory = productCategory,
        imageUrl = imageUrl,
        pizzaToppings = toppings?.map { it.toPizzaTopping() }
    )
}

fun Topping.toPizzaTopping(): PizzaTopping {
    return PizzaTopping(
        toppingUi = this.toToppingUi(),
        isSelected = false,
        quantity = 0

    )
}