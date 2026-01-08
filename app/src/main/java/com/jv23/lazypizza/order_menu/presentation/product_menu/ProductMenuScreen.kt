package com.jv23.lazypizza.order_menu.presentation.product_menu

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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jv23.lazypizza.R
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_SearchRefraction
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import com.jv23.lazypizza.core.presentation.designsystem.theme.bg
import com.jv23.lazypizza.core.presentation.designsystem.theme.body3Medium
import com.jv23.lazypizza.core.presentation.designsystem.theme.lazyPizzaOutline
import com.jv23.lazypizza.core.presentation.designsystem.theme.surfaceHigher
import com.jv23.lazypizza.core.presentation.designsystem.theme.textPrimary
import com.jv23.lazypizza.core.presentation.designsystem.theme.textSecondary
import com.jv23.lazypizza.order_menu.presentation.product_menu.components.LazyPizzaSearchBar
import com.jv23.lazypizza.order_menu.presentation.product_menu.components.LazyPizzaTopAppBar
import com.jv23.lazypizza.order_menu.presentation.product_menu.components.ProductCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductMenuScreenRoot(
    onMenuItemClick: (String) -> Unit,
    viewModel: ProductMenuViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProductMenuScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is ProductMenuAction.OnMenuItemCardClick-> { onMenuItemClick(action.menuCardItem.productUi.id)}
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun ProductMenuScreen(
    onAction: (ProductMenuAction) -> Unit,
    state: ProductMenuState,
) {
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
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
                    state = state.searchInput,
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
                            onClick = { onAction(ProductMenuAction.OnChangeProductCategoryFilter(categoryFilter = productCategory)) },
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
            // Products List

            // 1. Filter logic (Prepare the data)
            val itemsToShow = if (state.categoryFilter != null) {
                // If a specific filter is active, only show that category
                state.menuCardItems.filter { it.productUi.productCategory == state.categoryFilter }
            } else {
                // "Else" case: Show everything
                state.menuCardItems
            }

            // 2. Group the data by Category
            // This creates a Map<ProductCategory, List<MenuCardItem>>
            val groupedItems = itemsToShow.groupBy { it.productUi.productCategory }

            // 3. Iterate over the groups to render headers and items
            groupedItems.forEach { (category, itemsInThisCategory) ->

                // THE STICKY HEADER
                stickyHeader {
                    CategoryHeader(
                        text = category.name
                    )
                }

                // THE ITEMS FOR THIS CATEGORY
                items(
                    items = itemsInThisCategory,
                    key = { it.productUi.id } // performance optimization
                ) { item ->
                    ProductCard(
                        modifier = Modifier,
                        onAction = onAction,
                        state = state,
                        menuCardItem = item
                    )
                }
            }
        }
    }
}
@Composable
fun CategoryHeader(
    text: String
) {
    Surface(
        color = MaterialTheme.colorScheme.bg,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.textSecondary
        )
    }
}

@Preview
@Composable
private fun ProductMenuScreenPreview() {
    LazyPizzaTheme {
        ProductMenuScreen(
            state = ProductMenuState(
                categoryFilter = ProductCategory.PIZZA
            ),
            onAction = {}
        )
    }

}