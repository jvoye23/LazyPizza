package com.jv23.lazypizza.order_menu.presentation.product_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jv23.lazypizza.R
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_Minus
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_Plus
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import com.jv23.lazypizza.core.presentation.designsystem.theme.body3Regular
import com.jv23.lazypizza.core.presentation.designsystem.theme.lazyPizzaOutline
import com.jv23.lazypizza.core.presentation.designsystem.theme.surfaceHigher
import com.jv23.lazypizza.core.presentation.designsystem.theme.textPrimary
import com.jv23.lazypizza.core.presentation.designsystem.theme.textSecondary
import com.jv23.lazypizza.core.presentation.designsystem.theme.title2
import com.jv23.lazypizza.core.presentation.designsystem.util.toCurrencyString
import com.jv23.lazypizza.core.presentation.model.ProductUi
import com.jv23.lazypizza.core.presentation.model.ToppingUi
import com.jv23.lazypizza.order_menu.presentation.product_detail.PizzaTopping
import com.jv23.lazypizza.order_menu.presentation.product_detail.ProductDetailAction
import com.jv23.lazypizza.order_menu.presentation.product_detail.ProductDetailState
import java.text.NumberFormat
import java.util.Locale


@Composable
fun PizzaToppingCard(
    modifier: Modifier = Modifier,
    onAction: (ProductDetailAction) -> Unit,
    state: ProductDetailState,
    pizzaToppingItem: PizzaTopping,

    ) {
    Card(
        modifier = Modifier
            .height(142.dp)
            .width(121.dp)
            .clickable {
                onAction(ProductDetailAction.OnPizzaToppingCardClick( pizzaToppingItem = pizzaToppingItem))
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceHigher,
            contentColor = MaterialTheme.colorScheme.textPrimary,
            disabledContentColor = MaterialTheme.colorScheme.surfaceHigher,
            disabledContainerColor = MaterialTheme.colorScheme.textPrimary
        ),
        elevation = CardDefaults.cardElevation(1.dp),
        border = if (!pizzaToppingItem.isSelected)  {
            BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.lazyPizzaOutline)
        } else {
            BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(top = 8.dp, bottom = 12.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)

                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Image
                    SubcomposeAsyncImage(
                        model = pizzaToppingItem.toppingUi.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(5.dp))
                            .padding(2.dp),
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(12.dp),
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

                Text(
                    modifier = Modifier,
                    text = pizzaToppingItem.toppingUi.name,
                    style = MaterialTheme.typography.body3Regular,
                    color = MaterialTheme.colorScheme.textSecondary
                )

                if(!pizzaToppingItem.isSelected) {
                    Text(
                        modifier = Modifier,
                        text = pizzaToppingItem.toppingUi.price.toCurrencyString(),
                        style = MaterialTheme.typography.title2,
                        color = MaterialTheme.colorScheme.textPrimary
                    )
                } else {
                    ToppingCardItemCounter(
                        onPlusClick = {onAction(ProductDetailAction.OnPlusClick(pizzaToppingItem = pizzaToppingItem))},
                        onMinusClick = {onAction(ProductDetailAction.OnMinusClick(pizzaToppingItem = pizzaToppingItem))},
                        pizzaToppingItem = pizzaToppingItem
                    )
                }
            }
        }
    )
}

@Composable
private fun ToppingCardItemCounter (
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    pizzaToppingItem: PizzaTopping,

) {
    Row(
        modifier = Modifier
            .width(96.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable { onMinusClick() }
                .size(24.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceHigher,
                    shape = RoundedCornerShape(8.dp) // Apply shape here
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.lazyPizzaOutline,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(14.dp),
                tint = MaterialTheme.colorScheme.textSecondary,
                contentDescription = null,
                imageVector = Icon_Minus
            )
        }
        Text(
            modifier = Modifier
                .weight(1f)
            ,
            textAlign = TextAlign.Center,
            text = pizzaToppingItem.quantity.toString(),
            style = MaterialTheme.typography.title2,
            color = MaterialTheme.colorScheme.textPrimary

        )
        Box(
            modifier = Modifier
                .clickable { onPlusClick() }
                .size(24.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceHigher,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.lazyPizzaOutline,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(14.dp),
                tint = MaterialTheme.colorScheme.textSecondary,
                contentDescription = null,
                imageVector = Icon_Plus
            )
        }
    }
}

@Preview
@Composable
private fun PizzaToppingCardPreview() {
    LazyPizzaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PizzaToppingCard(
                modifier = Modifier,
                onAction = {},
                state = ProductDetailState(
                    productUi = ProductUi(
                        id = "123",
                        name = "Extra Cheese",
                        ingredients = "Tomato sauce, mozzarella, fresh basil, olive oil",
                        price = 199 / 100,
                        productCategory = ProductCategory.DRINKS,
                        imageUrl = "",
                        toppings = emptyList()
                    )
                ),
                pizzaToppingItem = PizzaTopping(
                    toppingUi = ToppingUi(
                        id = "123",
                        name = "Extra Cheese",
                        price = 50,
                        imageUrl = ""
                    )
                )
            )
        }
    }
}