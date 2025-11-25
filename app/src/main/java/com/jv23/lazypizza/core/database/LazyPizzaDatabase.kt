package com.jv23.lazypizza.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jv23.lazypizza.core.database.dao.ProductDao
import com.jv23.lazypizza.core.database.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)

abstract class LazyPizzaDatabase: RoomDatabase() {
    abstract val productDao: ProductDao
}
