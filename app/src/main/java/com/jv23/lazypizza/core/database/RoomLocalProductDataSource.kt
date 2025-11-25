package com.jv23.lazypizza.core.database

import android.database.sqlite.SQLiteFullException
import com.jv23.lazypizza.core.database.dao.ProductDao
import com.jv23.lazypizza.core.database.mappers.toDrink
import com.jv23.lazypizza.core.database.mappers.toIceCream
import com.jv23.lazypizza.core.database.mappers.toPizza
import com.jv23.lazypizza.core.database.mappers.toProductEntity
import com.jv23.lazypizza.core.database.mappers.toSauce
import com.jv23.lazypizza.core.database.mappers.toTopping
import com.jv23.lazypizza.core.domain.LocalProductDataSource
import com.jv23.lazypizza.core.domain.ProductItemId
import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.ProductCatalog
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.domain.util.DataError
import com.jv23.lazypizza.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class RoomLocalProductDataSource (
    private val productDao: ProductDao
): LocalProductDataSource {
    override fun getProducts(): Flow<List<Product>> {
        val pizzas = productDao.getProducts()
            .map { productEntities ->
                productEntities
                    .filter { it.productCategory == "PIZZA" }
                    .map { it.toPizza() }

            }

        val drinks = productDao.getProducts()
            .map { productEntities ->
                productEntities
                    .filter { it.productCategory == "DRINK" }
                    .map {
                        it.toDrink()
                    }
            }

        val iceCreams = productDao.getProducts()
            .map { productEntities ->
                productEntities
                    .filter { it.productCategory == "ICE CREAM" }
                    .map { it.toIceCream() }
            }

        val sauces = productDao.getProducts()
            .map { productEntities ->
                productEntities
                    .filter { it.productCategory == "SAUCE" }
                    .map { it.toSauce() }
            }

        val toppings = productDao.getProducts()
            .map { productEntities ->
                productEntities
                    .filter { it.productCategory == "TOPPING" }
                    .map { it.toTopping() }
            }

        return combine(pizzas, drinks, iceCreams, sauces, toppings) { pizzas, drinks, iceCreams, sauces, toppings ->
            (pizzas + drinks + iceCreams + sauces).sortedBy { it.id }
        }

    }


    override suspend fun getProductById(id: String): Product? {
        val product =  productDao.getProductById(id)
        return product.let { productEntity ->
            when (productEntity?.productCategory) {
                "PIZZA" -> productEntity.toPizza()
                "DRINK" -> productEntity.toDrink()
                "ICE_CREAM" -> productEntity.toIceCream()
                "SAUCE" -> productEntity.toSauce()
                "TOPPING" -> productEntity.toTopping()
                else -> null
            }
        }
    }

    override suspend fun upsertProductCatalog(productCatalog: ProductCatalog): Result<List<ProductItemId>, DataError.Local> {
        return try {
            println("CATALOG: $productCatalog")
            val pizzaEntities = productCatalog.pizzas.values.map { it.toProductEntity() }
            val drinkEntities = productCatalog.drinks.values.map { it.toProductEntity() }
            val iceCreamEntities = productCatalog.iceCreams.values.map { it.toProductEntity() }
            val sauceEntities = productCatalog.sauces.values.map { it.toProductEntity() }
            val toppingsEntities = productCatalog.toppings.values.map { it.toProductEntity() }

            productDao.upsertProducts(pizzaEntities)
            productDao.upsertProducts(drinkEntities)
            productDao.upsertProducts(iceCreamEntities)
            productDao.upsertProducts(sauceEntities)
            productDao.upsertProducts(toppingsEntities)

            Result.Success(
                data = productCatalog.pizzas.values.map { it.id } +
                        productCatalog.drinks.values.map { it.id } +
                        productCatalog.iceCreams.values.map { it.id } +
                        productCatalog.sauces.values.map { it.id } +
                        productCatalog.toppings.values.map { it.id }
            )

        } catch (e: SQLiteFullException) {
            Result.Error(error = DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteProductCatalog() {
        productDao.deleteAllProducts()
    }

}