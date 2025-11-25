package com.jv23.lazypizza.core.domain

import com.jv23.lazypizza.core.domain.model.ProductCatalog
import com.jv23.lazypizza.core.domain.util.DataError
import com.jv23.lazypizza.core.domain.util.Result

interface RemoteFirebaseProductDataSource {
    suspend fun fetchProductCatalog(): Result<ProductCatalog, DataError.Network>
}