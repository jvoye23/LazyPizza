package com.jv23.lazypizza.core.mapper

import com.jv23.lazypizza.core.data.networking.dto.ProductCatalogDto
import com.jv23.lazypizza.core.data.networking.dto.ProductDto
import com.jv23.lazypizza.core.data.networking.dto.ToppingDto
import com.jv23.lazypizza.core.domain.model.Product
import com.jv23.lazypizza.core.domain.model.ProductCatalog
import com.jv23.lazypizza.core.domain.model.Topping


fun ProductCatalogDto.toProductCatalog(): ProductCatalog {
    return ProductCatalog(
        products = products.mapValues { it.value.toProducts() },
        toppings = toppings.mapValues { it.value.toToppings() },
    )
}

fun ToppingDto.toToppings(): Topping {
    return Topping(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl
    )
}

fun ProductDto.toProducts(): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        productCategory = productCategory,
        imageUrl = imageUrl,
        ingredients = ingredients,
        toppings = toppings?.map { it.toToppings() }
    )
}