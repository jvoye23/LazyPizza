package com.jv23.lazypizza.order_cart.presentation.cart_overview

import com.jv23.lazypizza.core.domain.model.MenuCardItem

sealed interface CartOverviewAction {
    data object OnProceedToCheckoutButtonClick: CartOverviewAction
    data class OnPlusClick(val menuCardItem: MenuCardItem) : CartOverviewAction
    data class OnMinusClick(val menuCardItem: MenuCardItem) : CartOverviewAction
    data class OnTrashBinClick(val menuCardItem: MenuCardItem) : CartOverviewAction


}