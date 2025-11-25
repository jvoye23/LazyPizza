package com.jv23.lazypizza.core

import com.jv23.lazypizza.core.data.networking.dto.OfflineFirstProductRepository
import com.jv23.lazypizza.core.data.networking.dto.RemoteFirebaseProductDataSourceImpl
import com.jv23.lazypizza.core.domain.ProductRepository
import com.jv23.lazypizza.core.domain.RemoteFirebaseProductDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    singleOf(::OfflineFirstProductRepository).bind<ProductRepository>()
    singleOf(::RemoteFirebaseProductDataSourceImpl).bind<RemoteFirebaseProductDataSource>()
}