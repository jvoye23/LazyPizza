package com.jv23.lazypizza.core.domain

import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.ProductCatalog
import com.jv23.lazypizza.core.domain.model.Topping
import com.jv23.lazypizza.core.domain.util.DataError
import com.jv23.lazypizza.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias ProductItemId = String

interface LocalProductDataSource {
    fun getProducts(): Flow<List<Product>>
    fun getToppings(): Flow<List<Topping>>
    suspend fun getProductById(id: String): Product?
    suspend fun getToppingById(id: String): Topping?
    suspend fun upsertProductCatalog(productCatalog: ProductCatalog): Result<List<ProductItemId>, DataError.Local>
    suspend fun deleteProductCatalog()
}


