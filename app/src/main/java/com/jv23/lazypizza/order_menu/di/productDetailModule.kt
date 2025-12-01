package com.jv23.lazypizza.order_menu.di

import com.jv23.lazypizza.order_menu.presentation.product_detail.ProductDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val productDetailModule = module {

    viewModel { (productId: String) ->
        ProductDetailViewModel(
            productRepository = get(),
            productId = productId
        )
    }

}