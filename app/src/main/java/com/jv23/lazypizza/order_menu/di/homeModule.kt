package com.jv23.lazypizza.order_menu.di

import com.jv23.lazypizza.order_menu.presentation.product_menu.ProductMenuViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::ProductMenuViewModel)
}