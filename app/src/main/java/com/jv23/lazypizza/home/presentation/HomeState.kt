package com.jv23.lazypizza.home.presentation

import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.presentation.model.ProductUi


data class HomeState(
    val isProductDataLoaded: Boolean = false,
    val selectedMenuCardItem: MenuCardItem? = null,
    val isSelected: Boolean = false,

    val selectedItems: List<MenuCardItem> = emptyList(),
    val selectedItemId: String? = null,

    val selectedItemTotalPrice: String? = getItemTotalPriceById(selectedItems = selectedItems, selectedItemId = selectedItemId),
    val selectedItemQuantity: Int? = getItemQuantityById(selectedItems = selectedItems, selectedItemId = selectedItemId),

    val categoryFilter: ProductCategory? = null,

    val products: List<ProductUi> = emptyList<ProductUi>(),
    val menuCardItems: List<MenuCardItem> = emptyList()



)

private fun getItemTotalPriceById(selectedItems: List<MenuCardItem>, selectedItemId: String?) : String? {
    return if (selectedItemId != null) {
        val selectedItem = selectedItems.first { item ->
            item.productUi.id == selectedItemId
        }
        selectedItem.productUi.price

    } else {
        null
    }
}

private fun getItemQuantityById(selectedItems: List<MenuCardItem>, selectedItemId: String?): Int? {
    return if (selectedItemId != null) {
        val selectedItem = selectedItems.first { item ->
            item.productUi.id == selectedItemId
        }
        selectedItem.quantity
    } else {
        null
    }
}
