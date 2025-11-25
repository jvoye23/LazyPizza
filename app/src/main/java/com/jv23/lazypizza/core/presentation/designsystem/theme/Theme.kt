package com.jv23.lazypizza.core.presentation.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = TextOnPrimary,
)

val ColorScheme.textPrimary: Color
    get() = TextPrimary

val ColorScheme.textSecondary: Color
    get() = TextSecondary

val ColorScheme.textOnPrimary: Color
    get() = TextOnPrimary

val ColorScheme.bg: Color
    get() = BG

val ColorScheme.surfaceHigher: Color
    get() = SurfaceHigher

val ColorScheme.surfaceHighest: Color
    get() = SurfaceHighest

val ColorScheme.lazyPizzaOutline: Color
    get() = Outline

@Composable
fun LazyPizzaTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}