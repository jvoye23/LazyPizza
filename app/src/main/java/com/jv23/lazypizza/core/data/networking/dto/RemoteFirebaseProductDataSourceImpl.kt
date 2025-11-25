package com.jv23.lazypizza.core.data.networking.dto

import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.jv23.lazypizza.core.domain.RemoteFirebaseProductDataSource
import com.jv23.lazypizza.core.domain.model.ProductCatalog
import com.jv23.lazypizza.core.domain.util.DataError
import com.jv23.lazypizza.core.domain.util.Result
import com.jv23.lazypizza.core.mapper.toProductCatalog
import kotlinx.coroutines.tasks.await

class RemoteFirebaseProductDataSourceImpl(): RemoteFirebaseProductDataSource {
    override suspend fun fetchProductCatalog(): Result<ProductCatalog, DataError.Network> {
        val database = Firebase.database.reference
        val snapshot = database.get().await()
        val catalog = snapshot.getValue(ProductCatalogDto::class.java)

        return if (catalog != null) {
            println("FETCH Data: $catalog")
            Result.Success(catalog.toProductCatalog())
        } else {
            Result.Error(error = DataError.Network.SERIALIZATION)
        }
    }
}