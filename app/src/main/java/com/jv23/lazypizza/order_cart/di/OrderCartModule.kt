package com.jv23.lazypizza.order_cart.di

import com.jv23.lazypizza.core.domain.model.OrderCart
import com.jv23.lazypizza.order_cart.presentation.cart_overview.CartOverviewViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val orderCartModule = module {

    viewModel { (orderCart: OrderCart) ->
        CartOverviewViewModel(
            orderCart = orderCart
        )
    }
}