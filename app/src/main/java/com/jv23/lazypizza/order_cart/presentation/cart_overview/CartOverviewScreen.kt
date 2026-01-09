package com.jv23.lazypizza.order_cart.presentation.cart_overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.presentation.designsystem.buttons.GradientButton
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_ArrowLeft
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import com.jv23.lazypizza.core.presentation.designsystem.theme.bg
import com.jv23.lazypizza.core.presentation.designsystem.theme.body1Medium
import com.jv23.lazypizza.core.presentation.designsystem.theme.surfaceHigher
import com.jv23.lazypizza.core.presentation.designsystem.theme.textPrimary
import com.jv23.lazypizza.core.presentation.designsystem.theme.textSecondary
import com.jv23.lazypizza.core.presentation.designsystem.util.toCurrencyString
import com.jv23.lazypizza.core.presentation.model.ProductUi
import com.jv23.lazypizza.core.presentation.model.ToppingUi
import com.jv23.lazypizza.order_cart.presentation.cart_overview.components.OrderProductCard
import com.jv23.lazypizza.order_menu.presentation.product_detail.PizzaTopping
import com.jv23.lazypizza.order_menu.presentation.product_detail.ProductDetailAction
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartOverviewScreenRoot(
    onNavigateToCheckoutScreen: () -> Unit,
    viewModel: CartOverviewViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CartOverviewScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is CartOverviewAction.OnProceedToCheckoutButtonClick-> onNavigateToCheckoutScreen()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartOverviewScreen(
    state: CartOverviewState,
    onAction: (CartOverviewAction) -> Unit,

    ) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.bg,
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        modifier = Modifier,
                        text = "Cart",
                        style = MaterialTheme.typography.body1Medium,
                        color = MaterialTheme.colorScheme.textPrimary
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.bg,
                ),
                scrollBehavior = scrollBehavior
            )
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.bg)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 100.dp)
            ) {
                items(
                    items = state.orderCartItems,
                    key = { it.productUi.id }
                ) { item ->
                    OrderProductCard(
                        modifier = Modifier,
                        onAction = onAction,
                        state = state,
                        menuCardItem = item
                    )
                }
            }

            GradientButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 32.dp),
                buttonText = "Proceed to Checkout ${state.totalCartValue.toCurrencyString()}",
                colors = listOf(Color(0xFFF36B50), Color(0xFFF9966F)),
                shadowColor = Color(0xFFF36B50),
                enabled = true
            )
        }
    }
}

@Preview
@Composable
private fun CartOverviewScreenPreview() {
    LazyPizzaTheme {
        CartOverviewScreen(
            state = CartOverviewState(
                totalCartValue = 2549,
                orderCartItems = listOf<MenuCardItem>(
                    MenuCardItem(
                        productUi = ProductUi(
                            id = "123",
                            name = "Margherita",
                            ingredients = "Tomato sauce, mozzarella, fresh basil, olive oil",
                            price = 1099,
                            productCategory = ProductCategory.DRINKS,
                            imageUrl = "",
                            pizzaToppings = listOf<PizzaTopping>(
                                PizzaTopping(
                                    toppingUi = ToppingUi(
                                        id = "1",
                                        name = "Extra Cheese",
                                        price = 100,
                                        imageUrl = "",
                                    ),
                                    isSelected = true,
                                    quantity = 1
                                ),
                                PizzaTopping(
                                    toppingUi = ToppingUi(
                                        id = "2",
                                        name = "Pepperoni",
                                        price = 100,
                                        imageUrl = "",
                                    ),
                                    isSelected = true,
                                    quantity = 1
                                )
                            ),
                        ),
                        quantity = 4
                    )
                ),

            ),
            onAction = {}
        )
    }
    
}