package com.jv23.lazypizza.core.presentation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jv23.lazypizza.R

val InstrumentSans = FontFamily(
    Font(
        resId = R.font.instrument_sans_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.instrument_sans_semi_bold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.instrument_sans_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.instrument_sans_bold,
        weight = FontWeight.Bold
    )
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val Typography.title1SemiBold: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 28.sp
    )

val Typography.title2: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp
    )

val Typography.title3: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 22.sp
    )

val Typography.label2SemiBold: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )

val Typography.body1Regular: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    )

val Typography.body1Medium: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp
    )

val Typography.body3Regular: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )

val Typography.body3Medium: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )

val Typography.body3Bold: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )

val Typography.body4Regular: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )

