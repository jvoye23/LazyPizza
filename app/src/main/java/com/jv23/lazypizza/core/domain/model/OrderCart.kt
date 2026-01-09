package com.jv23.lazypizza.core.domain.model

data class OrderCart(
    val items: List<MenuCardItem> = emptyList()
) {
    val totalPrice: Int
        get() = items.sumOf { it.productUi.price * it.quantity }
    val totalQuantity: Int
        get() = items.sumOf { it.quantity }
}
