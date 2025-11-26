package com.jv23.lazypizza.core.database

import android.database.sqlite.SQLiteFullException
import com.jv23.lazypizza.core.database.dao.ProductDao
import com.jv23.lazypizza.core.database.dao.ToppingDao
import com.jv23.lazypizza.core.database.mappers.toProduct
import com.jv23.lazypizza.core.database.mappers.toProductEntity
import com.jv23.lazypizza.core.database.mappers.toTopping
import com.jv23.lazypizza.core.database.mappers.toToppingEntity
import com.jv23.lazypizza.core.domain.LocalProductDataSource
import com.jv23.lazypizza.core.domain.ProductItemId
import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.ProductCatalog
import com.jv23.lazypizza.core.domain.model.Topping
import com.jv23.lazypizza.core.domain.util.DataError
import com.jv23.lazypizza.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalProductDataSource (
    private val productDao: ProductDao,
    private val toppingDao: ToppingDao
): LocalProductDataSource {
    override fun getProducts(): Flow<List<Product>> {
        val products = productDao.getProducts()
            .map { productEntities ->
                productEntities.map { it.toProduct() }
            }
        return products
    }

    override fun getToppings(): Flow<List<Topping>> {
        val toppings = toppingDao.getToppings()
            .map { toppingEntities ->
                toppingEntities.map { it.toTopping() }
            }
        return toppings
    }


    override suspend fun getProductById(id: String): Product? {
        return productDao.getProductById(id)?.toProduct()
    }

    override suspend fun getToppingById(id: String): Topping? {
        return toppingDao.getToppingById(id)?.toTopping()
    }

    override suspend fun upsertProductCatalog(productCatalog: ProductCatalog): Result<List<ProductItemId>, DataError.Local> {
        return try {

            val productEntities = productCatalog.products.values.map { it.toProductEntity() }
            val toppingEntities = productCatalog.toppings.values.map { it.toToppingEntity() }

            productDao.upsertProducts(productEntities)
            toppingDao.upsertToppings(toppingEntities)

            Result.Success(
                data = productCatalog.products.values.map { it.id } +
                        productCatalog.toppings.values.map { it.id }

            )

        } catch (e: SQLiteFullException) {
            Result.Error(error = DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteProductCatalog() {
        productDao.deleteAllProducts()
        toppingDao.deleteAllToppings()
    }

}