package com.jv23.lazypizza.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jv23.lazypizza.core.database.dao.ProductDao
import com.jv23.lazypizza.core.database.dao.ToppingDao
import com.jv23.lazypizza.core.database.entity.ProductEntity
import com.jv23.lazypizza.core.database.entity.ToppingEntity
import com.jv23.lazypizza.core.database.mappers.RoomConverters

@Database(
    entities = [ProductEntity::class, ToppingEntity::class],
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class LazyPizzaDatabase: RoomDatabase() {
    abstract val productDao: ProductDao
    abstract val toppingDao: ToppingDao
}
