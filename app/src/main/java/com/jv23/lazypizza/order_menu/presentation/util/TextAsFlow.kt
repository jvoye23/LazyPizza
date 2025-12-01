package com.jv23.lazypizza.order_menu.presentation.util

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow

fun TextFieldState.textAsFlow() = snapshotFlow { text }