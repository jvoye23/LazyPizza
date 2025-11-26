package com.jv23.lazypizza.home.presentation

import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.domain.model.ProductCategory


sealed interface HomeAction {
    data class OnMenuItemCardClick(val menuCardItem: MenuCardItem): HomeAction
    data class OnChangeProductCategoryFilter(val categoryFilter: ProductCategory): HomeAction

}

