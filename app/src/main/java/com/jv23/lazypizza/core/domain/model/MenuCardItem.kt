package com.jv23.lazypizza.core.domain.model

import com.jv23.lazypizza.core.presentation.model.ProductUi

data class MenuCardItem(
    val productUi: ProductUi,
    val quantity: Int
)