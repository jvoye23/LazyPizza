package com.jv23.lazypizza.order_menu.presentation.product_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailScreenRoot(
    viewModel: ProductDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProductDetailScreen(
        state = state,
        onAction = viewModel::onAction
    )

}

@Composable
fun ProductDetailScreen(
   state: ProductDetailState,
   onAction: (ProductDetailAction) -> Unit,
) {

}

@Preview
@Composable
private fun ProductDetailScreenPreview() {
    LazyPizzaTheme {
        ProductDetailScreen(
            state = ProductDetailState(),
            onAction = {}
        )
    }
}