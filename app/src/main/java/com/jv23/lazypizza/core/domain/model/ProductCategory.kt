package com.jv23.lazypizza.core.domain.model

import com.jv23.lazypizza.R
import com.jv23.lazypizza.core.presentation.designsystem.util.UiText

enum class ProductCategory(val label: UiText) {
    PIZZA(label = UiText.StringResource(R.string.pizza)),
    DRINKS(label = UiText.StringResource(R.string.drinks)),
    SAUCES(label = UiText.StringResource(R.string.sauces)),
    ICE_CREAM(label = UiText.StringResource(R.string.ice_cream))
}