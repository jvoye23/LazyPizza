package com.jv23.lazypizza.core.domain

import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.util.DataError
import com.jv23.lazypizza.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun fetchProductCatalog(): EmptyResult<DataError>
    fun getProductItems(): Flow<List<Product>>
}