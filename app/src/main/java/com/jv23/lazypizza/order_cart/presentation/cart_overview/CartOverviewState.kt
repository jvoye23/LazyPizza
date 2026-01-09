package com.jv23.lazypizza.order_cart.presentation.cart_overview

import com.jv23.lazypizza.core.domain.model.MenuCardItem

data class CartOverviewState(
    val orderCartItems: List<MenuCardItem> = emptyList(),
    val totalCartValue: Int = 0
)