package com.jv23.lazypizza.core.data.networking.dto

import com.jv23.lazypizza.core.domain.LocalProductDataSource
import com.jv23.lazypizza.core.domain.ProductRepository
import com.jv23.lazypizza.core.domain.RemoteFirebaseProductDataSource
import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.Topping
import com.jv23.lazypizza.core.domain.util.DataError
import com.jv23.lazypizza.core.domain.util.EmptyResult
import com.jv23.lazypizza.core.domain.util.Result
import com.jv23.lazypizza.core.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class OfflineFirstProductRepository(
    private val applicationScope: CoroutineScope,
    private val localProductDataSource: LocalProductDataSource,
    private val remoteFirebaseProductDataSource: RemoteFirebaseProductDataSource
): ProductRepository {

    override suspend fun fetchProductCatalog(): EmptyResult<DataError> {
        return when(val result = remoteFirebaseProductDataSource.fetchProductCatalog()) {
            is Result.Error -> result.asEmptyDataResult()
            is Result.Success -> {
                applicationScope.async {
                    localProductDataSource.upsertProductCatalog(productCatalog = result.data).asEmptyDataResult()
                }.await()
            }
        }
    }

    override fun getProductItems(): Flow<List<Product>> {
        return localProductDataSource.getProducts()
    }

    override fun getToppingItems(): Flow<List<Topping>> {
        return localProductDataSource.getToppings()
    }
}