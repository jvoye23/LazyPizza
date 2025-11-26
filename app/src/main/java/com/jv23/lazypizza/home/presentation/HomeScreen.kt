package com.jv23.lazypizza.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jv23.lazypizza.R
import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_SearchRefraction
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import com.jv23.lazypizza.core.presentation.designsystem.theme.bg
import com.jv23.lazypizza.core.presentation.designsystem.theme.body3Medium
import com.jv23.lazypizza.core.presentation.designsystem.theme.lazyPizzaOutline
import com.jv23.lazypizza.core.presentation.designsystem.theme.surfaceHigher
import com.jv23.lazypizza.core.presentation.designsystem.theme.textPrimary
import com.jv23.lazypizza.core.presentation.model.ProductUi
import com.jv23.lazypizza.home.presentation.components.LazyPizzaSearchBar
import com.jv23.lazypizza.home.presentation.components.LazyPizzaTopAppBar
import com.jv23.lazypizza.home.presentation.components.ProductCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = Modifier,
        onAction = viewModel::onAction,
        state = state
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAction: (HomeAction) -> Unit,
    state: HomeState,
) {
    /*val filteredProducts: Map<Boolean, List<ProductUi>> = remember(state.products) {
        state.products.groupBy { it.productCategory == state.categoryFilter }
    }*/

    val filteredProducts: List<MenuCardItem> = remember(state.menuCardItems) {
        state.menuCardItems.filter { it.productUi.productCategory == state.categoryFilter }
    }
    /*{
        state.menuCardItems.filter { it.productUi.productCategory == state.categoryFilter }
    }*/

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.bg,
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            LazyPizzaTopAppBar()
        },

        ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(R.drawable.pizza_banner),
                    contentDescription = null,
                    contentScale = ContentScale.Crop

                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                LazyPizzaSearchBar(
                    modifier = Modifier,
                    state = TextFieldState(),
                    leadingIcon = Icon_SearchRefraction,
                    containerColor = MaterialTheme.colorScheme.surfaceHigher,
                    hint = stringResource(R.string.search_for_delicious_food),
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ProductCategory.entries.forEach { productCategory ->
                        FilterChip(
                            modifier = Modifier.wrapContentWidth(),
                            selected = state.categoryFilter == productCategory,
                            onClick = { onAction(HomeAction.OnChangeProductCategoryFilter(categoryFilter = productCategory)) },
                            label = {
                                Text(
                                    modifier = Modifier,
                                    text = productCategory.label.asString(),
                                    style = MaterialTheme.typography.body3Medium,
                                    textAlign = TextAlign.Center,
                                    maxLines = 1
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = MaterialTheme.colorScheme.bg,
                                selectedContainerColor = MaterialTheme.colorScheme.bg,
                                labelColor = MaterialTheme.colorScheme.textPrimary,
                                selectedLabelColor = MaterialTheme.colorScheme.primary
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                borderColor = MaterialTheme.colorScheme.lazyPizzaOutline,
                                enabled = true,
                                selected = state.categoryFilter == productCategory,
                                selectedBorderColor = MaterialTheme.colorScheme.primary,
                                disabledBorderColor = MaterialTheme.colorScheme.outline,
                                disabledSelectedBorderColor = MaterialTheme.colorScheme.outline,
                                borderWidth = 1.dp,
                                selectedBorderWidth = 1.dp
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                }
            }

            when(state.categoryFilter) {
                ProductCategory.PIZZA -> {
                    filteredProducts
                        .filter { it.productUi.productCategory == ProductCategory.PIZZA }

                    items(filteredProducts) { items ->
                        ProductCard(
                            modifier = Modifier,
                            onAction = onAction,
                            state = state,
                            menuCardItem = MenuCardItem(
                                productUi = items.productUi,
                                quantity = items.quantity
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                ProductCategory.DRINKS -> {
                    filteredProducts.filter { it.productUi.productCategory == ProductCategory.DRINKS }
                    items(filteredProducts) { items ->
                        ProductCard(
                            modifier = Modifier,
                            onAction = onAction,
                            state = state,
                            menuCardItem = MenuCardItem(
                                productUi = items.productUi,
                                quantity = items.quantity
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                ProductCategory.ICE_CREAM -> {
                    filteredProducts.filter { it.productUi.productCategory == ProductCategory.ICE_CREAM }
                    items(filteredProducts) { items ->
                        ProductCard(
                            modifier = Modifier,
                            onAction = onAction,
                            state = state,
                            menuCardItem = MenuCardItem(
                                productUi = items.productUi,
                                quantity = items.quantity
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                ProductCategory.SAUCES -> {
                    filteredProducts.filter { it.productUi.productCategory == ProductCategory.SAUCES }
                    items(filteredProducts) { items ->
                        ProductCard(
                            modifier = Modifier,
                            onAction = onAction,
                            state = state,
                            menuCardItem = MenuCardItem(
                                productUi = items.productUi,
                                quantity = items.quantity
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                else -> {
                    items(state.menuCardItems) { items ->
                        ProductCard(
                            modifier = Modifier,
                            onAction = onAction,
                            state = state,
                            menuCardItem = MenuCardItem(
                                productUi = items.productUi,
                                quantity = items.quantity
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            /*items(state.menuCardItems) { items ->
                ProductCard(
                    modifier = Modifier,
                    onAction = onAction,
                    state = state,
                    menuCardItem = MenuCardItem(
                        productUi = items.productUi,
                        quantity = items.quantity
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }*/

        }

    }





}

@Preview
@Composable
private fun HomeScreenPreview() {
    LazyPizzaTheme {
        HomeScreen(
            state = HomeState(
                categoryFilter = ProductCategory.PIZZA
            ),
            onAction = {}
        )
    }

}