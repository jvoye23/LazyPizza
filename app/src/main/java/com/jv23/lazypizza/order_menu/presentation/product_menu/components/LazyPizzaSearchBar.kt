package com.jv23.lazypizza.order_menu.presentation.product_menu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_SearchRefraction
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import com.jv23.lazypizza.core.presentation.designsystem.theme.bg
import com.jv23.lazypizza.core.presentation.designsystem.theme.body1Regular
import com.jv23.lazypizza.core.presentation.designsystem.theme.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyPizzaSearchBar(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    leadingIcon: ImageVector?,
    containerColor: Color = MaterialTheme.colorScheme.bg,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
) {
    var isFocused by rememberSaveable {
        mutableStateOf(false)
    }

    BasicTextField(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(
                containerColor
            )
            .padding(20.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        state = state,
        textStyle = MaterialTheme.typography.body1Regular.copy(
            color = MaterialTheme.colorScheme.textSecondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.textSecondary),

        decorator = { innerBox ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null){
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (state.text.isEmpty() && !isFocused) {
                        Text(
                            text = hint,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                alpha = 0.7f
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    innerBox()
                }
            }
        }
    )
}

@Preview
@Composable
private fun LazyPizzaSearchBarPreview() {
    LazyPizzaTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyPizzaSearchBar(
                state = rememberTextFieldState(),
                leadingIcon = Icon_SearchRefraction,
                hint = "Search for delicious food...",
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}