package com.jv23.lazypizza.core.mapper

import com.jv23.lazypizza.core.data.networking.dto.DrinkDto
import com.jv23.lazypizza.core.data.networking.dto.IceCreamDto
import com.jv23.lazypizza.core.data.networking.dto.PizzaDto
import com.jv23.lazypizza.core.data.networking.dto.ProductCatalogDto
import com.jv23.lazypizza.core.data.networking.dto.SaucesDto
import com.jv23.lazypizza.core.data.networking.dto.ToppingsDto
import com.jv23.lazypizza.core.domain.model.Drink
import com.jv23.lazypizza.core.domain.model.IceCream
import com.jv23.lazypizza.core.domain.model.Pizza
import com.jv23.lazypizza.core.domain.model.ProductCatalog
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.domain.model.Sauce
import com.jv23.lazypizza.core.domain.model.Topping


fun ProductCatalogDto.toProductCatalog(): ProductCatalog {
    return ProductCatalog(
        pizzas = pizzas.mapValues { it.value.toPizza() },
        toppings = toppings.mapValues { it.value.toToppings() },
        drinks = drinks.mapValues { it.value.toDrink() },
        iceCreams = iceCreams.mapValues { it.value.toIceCream() },
        sauces = sauces.mapValues { it.value.toSauces() },
    )
}

fun PizzaDto.toPizza(): Pizza {
    return Pizza(
        id = id,
        name = name,
        ingredients = ingredients,
        price = price,
        productCategory = ProductCategory.PIZZA,
        imageUrl = imageUrl,
        toppingIds = null,
    )
}

fun ToppingsDto.toToppings(): Topping {
    return Topping(
        id = id,
        name = name,
        price = price,
        productCategory = ProductCategory.PIZZA,
        imageUrl = imageUrl,
    )
}

fun DrinkDto.toDrink(): Drink {
    return Drink(
        id = id,
        name = name,
        price = price,
        productCategory = ProductCategory.DRINKS,
        imageUrl = imageUrl,
    )
}

fun IceCreamDto.toIceCream(): IceCream {
    return IceCream(
        id = id,
        name = name,
        price = price,
        productCategory = ProductCategory.ICE_CREAM,
        imageUrl = imageUrl
    )
}

fun SaucesDto.toSauces(): Sauce {
    return Sauce(
        id = id,
        name = name,
        price = price,
        productCategory = ProductCategory.SAUCES,
        imageUrl = imageUrl
    )
}