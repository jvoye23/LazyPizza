package com.jv23.lazypizza.order_menu.presentation.product_detail

sealed interface ProductDetailAction {
    data object OnClickAddToCart : ProductDetailAction
    data object OnClickMinus : ProductDetailAction
}