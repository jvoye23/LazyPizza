package com.jv23.lazypizza.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jv23.lazypizza.core.database.entity.ProductEntity
import com.jv23.lazypizza.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Upsert
    suspend fun upsertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id: String): ProductEntity?

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()
}