package com.jv23.lazypizza.order_menu.presentation.product_detail

import com.jv23.lazypizza.core.presentation.model.ProductUi
import com.jv23.lazypizza.core.presentation.model.ToppingUi

data class ProductDetailState(
    val productUi: ProductUi? = null,
    val totalCartValue: Int = 0,
    val pizzaToppings: List<PizzaTopping> = emptyList()
)

data class PizzaTopping(
    val toppingUi: ToppingUi,
    val isSelected: Boolean = false,
    val quantity: Int = 0
)

//val displayPrice = NumberFormat.getCurrencyInstance(Locale.US).format(price)