package com.jv23.lazypizza.order_menu.presentation.product_detail

sealed interface ProductDetailAction {
    data object OnNavigateBackClick: ProductDetailAction
    data object OnClickAddToCart : ProductDetailAction
    data class OnMinusClick(val pizzaToppingItem: PizzaTopping) : ProductDetailAction
    data class OnPlusClick(val pizzaToppingItem: PizzaTopping) : ProductDetailAction
    data class OnPizzaToppingCardClick(val pizzaToppingItem: PizzaTopping): ProductDetailAction
}