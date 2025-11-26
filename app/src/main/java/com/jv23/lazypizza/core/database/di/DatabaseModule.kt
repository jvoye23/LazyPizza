package com.jv23.lazypizza.core.database.di

import androidx.room.Room
import com.jv23.lazypizza.core.database.LazyPizzaDatabase
import com.jv23.lazypizza.core.database.RoomLocalProductDataSource
import com.jv23.lazypizza.core.domain.LocalProductDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = LazyPizzaDatabase::class.java,
            name = "lazyPizza.db"
        ).build()
    }
    single { get<LazyPizzaDatabase>().productDao }
    single { get<LazyPizzaDatabase>().toppingDao }

    singleOf(::RoomLocalProductDataSource).bind<LocalProductDataSource>()
}