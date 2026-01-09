package com.jv23.lazypizza.order_menu.presentation.product_menu

import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.order_menu.presentation.product_detail.PizzaTopping
import com.jv23.lazypizza.order_menu.presentation.product_detail.ProductDetailAction


sealed interface ProductMenuAction {
    data class OnMenuItemCardClick(val menuCardItem: MenuCardItem): ProductMenuAction
    data class OnChangeProductCategoryFilter(val categoryFilter: ProductCategory): ProductMenuAction

    data class OnAddToCartClick(val menuCardItem: MenuCardItem): ProductMenuAction

    data class OnTrashBinClick(val menuCardItem: MenuCardItem): ProductMenuAction

    data class OnMinusClick(val menuCardItem: MenuCardItem) : ProductMenuAction
    data class OnPlusClick(val menuCardItem: MenuCardItem) : ProductMenuAction


}

