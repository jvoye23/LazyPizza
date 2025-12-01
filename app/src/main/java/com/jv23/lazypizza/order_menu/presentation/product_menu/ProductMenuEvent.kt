package com.jv23.lazypizza.order_menu.presentation.product_menu

sealed interface ProductMenuEvent {
    data object OnSelected: ProductMenuEvent
}