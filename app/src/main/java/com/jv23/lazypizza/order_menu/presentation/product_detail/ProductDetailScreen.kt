package com.jv23.lazypizza.order_menu.presentation.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.jv23.lazypizza.R
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.presentation.designsystem.buttons.GradientButton
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_ArrowLeft
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import com.jv23.lazypizza.core.presentation.designsystem.theme.bg
import com.jv23.lazypizza.core.presentation.designsystem.theme.body3Regular
import com.jv23.lazypizza.core.presentation.designsystem.theme.label2SemiBold
import com.jv23.lazypizza.core.presentation.designsystem.theme.surfaceHigher
import com.jv23.lazypizza.core.presentation.designsystem.theme.textPrimary
import com.jv23.lazypizza.core.presentation.designsystem.theme.textSecondary
import com.jv23.lazypizza.core.presentation.designsystem.theme.title1SemiBold
import com.jv23.lazypizza.core.presentation.designsystem.util.toCurrencyString
import com.jv23.lazypizza.core.presentation.model.ProductUi
import com.jv23.lazypizza.order_menu.presentation.product_detail.components.PizzaToppingCard
import org.koin.androidx.compose.koinViewModel
import java.util.Locale.getDefault

@Composable
fun ProductDetailScreenRoot(
    onNavigateBackClick: () -> Unit,
    viewModel: ProductDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProductDetailScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is ProductDetailAction.OnNavigateBackClick -> onNavigateBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
   state: ProductDetailState,
   onAction: (ProductDetailAction) -> Unit,
) {

    val scrollState = rememberLazyGridState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.bg,
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            TopAppBar(
                title = {},
                modifier = Modifier
                    .fillMaxWidth(),
                navigationIcon = {
                    IconButton(
                        modifier = Modifier
                            .padding(10.dp)
                            .background(
                                shape = RoundedCornerShape(50.dp),
                                color = MaterialTheme.colorScheme.textSecondary.copy(alpha = 0.08f)
                            )
                            .size(44.dp),
                        onClick = { onAction(ProductDetailAction.OnNavigateBackClick) }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icon_ArrowLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.textSecondary
                        )
                    }
                } ,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.bg,
                ),
                scrollBehavior = scrollBehavior
            )
        },

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surfaceHigher)
        ) {
            val horizontalPadding = 16.dp
            LazyVerticalGrid(
                state = scrollState,
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = horizontalPadding),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp)
                    ,
            ) {
                // 1st block
                item(
                    span = {GridItemSpan(maxLineSpan)} // Spans all 3 cells
                ) {
                    SubcomposeAsyncImage(
                        model = state.productUi?.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .horizontalPaddingStretch(horizontalPadding)
                            .height(240.dp)
                            .clip(RoundedCornerShape(bottomEnd = 16.dp))
                            .background(MaterialTheme.colorScheme.bg),
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 2.dp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        },
                        error = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.errorContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.error_couldnt_load_image),
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    )

                }

                // 2nd block
                item(
                    span = {GridItemSpan(maxLineSpan)} // Spans all 3 cells
                ) {
                    Column(
                        modifier = Modifier
                            .horizontalPaddingStretch(horizontalPadding)
                            .background(MaterialTheme.colorScheme.bg)
                            .clip(RoundedCornerShape(topStart = 16.dp))
                            .background(MaterialTheme.colorScheme.surfaceHigher)
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            modifier = Modifier

                                .padding(start = 16.dp),
                            text = state.productUi?.name ?: "Product",
                            style = MaterialTheme.typography.title1SemiBold,
                            color = MaterialTheme.colorScheme.textPrimary
                        )
                        Text(
                            modifier = Modifier

                                .padding(start = 16.dp),
                            text = state.productUi?.ingredients ?: "Ingredients",
                            style = MaterialTheme.typography.body3Regular,
                            color = MaterialTheme.colorScheme.textSecondary
                        )
                    }

                }

                // 3rd block
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .horizontalPaddingStretch(horizontalPadding)
                            .background(MaterialTheme.colorScheme.surfaceHigher)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(R.string.add_extra_toppings).uppercase(getDefault()),
                            style = MaterialTheme.typography.label2SemiBold,
                            color = MaterialTheme.colorScheme.textSecondary
                        )
                    }

                }

                items(
                    items = state.pizzaToppings,
                    key = { it.toppingUi.id }
                ) {
                        item ->
                    PizzaToppingCard(
                        modifier = Modifier,
                        onAction = onAction,
                        state = state,
                        pizzaToppingItem = item
                    )
                }
            }

            GradientButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 32.dp),
                buttonText = "Add to Cart for ${state.totalCartValue.toCurrencyString()}",
                colors = listOf(Color(0xFFF36B50), Color(0xFFF9966F)),
                shadowColor = Color(0xFFF36B50),
                enabled = true
            )

        }
    }
}

fun Modifier.horizontalPaddingStretch(padding: Dp): Modifier = layout { measurable, constraints ->
    // 1. Calculate the FULL width (Screen Width)
    val extraWidth = padding.roundToPx() * 2
    val fullWidth = constraints.maxWidth + extraWidth

    // 2. Measure the content with the FULL width
    //    We force the child to be as wide as the screen.
    val placeable = measurable.measure(
        constraints.copy(
            minWidth = fullWidth,
            maxWidth = fullWidth
        )
    )

    // 3. Report the ORIGINAL (smaller) width to the parent (Grid)
    //    We tell the Grid: "I fit perfectly in your 16dp padded slot."
    layout(constraints.maxWidth, placeable.height) {
        // 4. Place the huge content shifted to the left
        //    It will visually overflow the reported bounds, effectively bleeding to the edges.
        placeable.place(x = -padding.roundToPx(), y = 0)
    }
}

@Preview
@Composable
private fun ProductDetailScreenPreview() {
    LazyPizzaTheme {
        ProductDetailScreen(
            state = ProductDetailState(
                productUi = ProductUi(
                    id = "123",
                    name = "Margherita",
                    ingredients = "Tomato sauce, mozzarella, fresh basil, olive oil",
                    price = 899,
                    productCategory = ProductCategory.DRINKS,
                    imageUrl = "",
                    toppings = emptyList(),
                )
            ),
            onAction = {}
        )
    }
}