package com.jv23.lazypizza.app.di

import com.jv23.lazypizza.app.LazyPizzaApp
import com.jv23.lazypizza.app.MainViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as LazyPizzaApp).applicationScope
    }

    viewModelOf(::MainViewModel)


}