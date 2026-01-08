package com.jv23.lazypizza.core.presentation.designsystem.util

import java.text.NumberFormat
import java.util.Locale

/**
 * Converts an Integer price in cents (e.g. 1849) to a formatted currency string (e.g. "$18.49")
 * automatically handling local symbols ($ or €) and formatting.
 */
fun Int.toCurrencyString(locale: Locale = Locale.getDefault()): String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    // We simply divide by 100.0 to convert Cents -> Dollars/Euros
    return formatter.format(this / 100.0)
}


//val displayPrice = NumberFormat.getCurrencyInstance(Locale.US).format(price)