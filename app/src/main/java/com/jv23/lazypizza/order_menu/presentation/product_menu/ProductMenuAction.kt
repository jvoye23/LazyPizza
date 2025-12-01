package com.jv23.lazypizza.order_menu.presentation.product_menu

import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.domain.model.ProductCategory


sealed interface ProductMenuAction {
    data class OnMenuItemCardClick(val menuCardItem: MenuCardItem): ProductMenuAction
    data class OnChangeProductCategoryFilter(val categoryFilter: ProductCategory): ProductMenuAction

}

