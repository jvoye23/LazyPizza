package com.jv23.lazypizza.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jv23.lazypizza.R
import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_Minus
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_Plus
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_Trash
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import com.jv23.lazypizza.core.presentation.designsystem.theme.body1Medium
import com.jv23.lazypizza.core.presentation.designsystem.theme.body3Regular
import com.jv23.lazypizza.core.presentation.designsystem.theme.body4Regular
import com.jv23.lazypizza.core.presentation.designsystem.theme.surfaceHigher
import com.jv23.lazypizza.core.presentation.designsystem.theme.surfaceHighest
import com.jv23.lazypizza.core.presentation.designsystem.theme.textPrimary
import com.jv23.lazypizza.core.presentation.designsystem.theme.textSecondary
import com.jv23.lazypizza.core.presentation.designsystem.theme.title1SemiBold
import com.jv23.lazypizza.core.presentation.designsystem.theme.title2
import com.jv23.lazypizza.core.presentation.designsystem.theme.title3
import com.jv23.lazypizza.core.presentation.model.ProductUi
import com.jv23.lazypizza.home.presentation.HomeAction
import com.jv23.lazypizza.home.presentation.HomeState

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    onAction: (HomeAction) -> Unit,
    state: HomeState,
    menuCardItem: MenuCardItem,

    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(124.dp)
            .clickable {
                onAction(HomeAction.OnMenuItemCardClick(menuCardItem))
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceHigher,
            contentColor = MaterialTheme.colorScheme.textPrimary,
            disabledContentColor = MaterialTheme.colorScheme.surfaceHigher,
            disabledContainerColor = MaterialTheme.colorScheme.textPrimary
        ),
        elevation = CardDefaults.cardElevation(1.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surfaceHigher),
        content = {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Box(
                    modifier = Modifier
                        .weight(0.3f)
                        .fillMaxHeight()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceHighest,
                            shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)

                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Image
                    SubcomposeAsyncImage(
                        model = menuCardItem.productUi.imageUrl,
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

                Column(
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 12.dp)

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            text = menuCardItem.productUi.name,
                            style = MaterialTheme.typography.body1Medium,
                            color = MaterialTheme.colorScheme.textPrimary
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        if (menuCardItem.quantity != 0) {
                            Box(
                                modifier = Modifier
                                    .clickable { }
                                    .size(24.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceHigher,
                                        shape = RoundedCornerShape(8.dp) // Apply shape here
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                        shape = RoundedCornerShape(8.dp) // <-- Add the shape here
                                    )
                                    .clip(RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(14.dp),
                                    tint = MaterialTheme.colorScheme.primary,
                                    contentDescription = null,
                                    imageVector = Icon_Trash
                                )
                            }
                        }

                    }

                    if (menuCardItem.productUi.ingredients != null) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = menuCardItem.productUi.ingredients,
                            style = MaterialTheme.typography.body3Regular,
                            color = MaterialTheme.colorScheme.textSecondary
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    if (menuCardItem.quantity == 0) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(
                                modifier = Modifier,
                                text = menuCardItem.productUi.price,
                                style = MaterialTheme.typography.title1SemiBold,
                                color = MaterialTheme.colorScheme.textPrimary
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            if (menuCardItem.productUi.productCategory != ProductCategory.PIZZA) {
                                OutlinedButton(
                                    modifier = Modifier
                                        .height(40.dp),
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceHigher,
                                        contentColor = MaterialTheme.colorScheme.primary
                                    ),
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                                    )

                                ) {
                                    Text(
                                        text = stringResource(R.string.add_to_cart),
                                        style = MaterialTheme.typography.title3,
                                        color = MaterialTheme.colorScheme.primary
                                    )

                                }
                            }

                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MenuCardItemCounter(
                                onPlusClick = {},
                                onMinusClick = {},
                                state = state,
                                menuCardItem = menuCardItem
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Column(
                                modifier = Modifier,
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = state.selectedItemTotalPrice ?: menuCardItem.productUi.price,
                                    style = MaterialTheme.typography.title1SemiBold,
                                    color = MaterialTheme.colorScheme.textPrimary
                                )
                                Text(
                                    modifier = Modifier,
                                    text = state.selectedItemQuantity.toString() + " x " + state.selectedMenuCardItem?.productUi?.price,
                                    style = MaterialTheme.typography.body4Regular,
                                    color = MaterialTheme.colorScheme.textSecondary
                                )
                            }

                        }

                    }


                }
            }
        }
    )
}

@Composable
private fun MenuCardItemCounter (
    modifier: Modifier = Modifier,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    state: HomeState,
    menuCardItem: MenuCardItem
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
                   color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                   shape = RoundedCornerShape(8.dp) // <-- Add the shape here
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
           text = menuCardItem.quantity.toString(),
           style = MaterialTheme.typography.title2,
           color = MaterialTheme.colorScheme.textPrimary

       )
       Box(
           modifier = Modifier
               .clickable { onPlusClick() }
               .size(24.dp)
               .background(
                   color = MaterialTheme.colorScheme.surfaceHigher,
                   shape = RoundedCornerShape(8.dp) // Apply shape here
               )
               .border(
                   width = 1.dp,
                   color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                   shape = RoundedCornerShape(8.dp) // <-- Add the shape here
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
private fun PizzaCardPreview() {

    LazyPizzaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { 
            ProductCard(
                modifier = Modifier,
                onAction = {},
                state = HomeState(
                    selectedMenuCardItem = MenuCardItem(
                        productUi = ProductUi(
                            id = "123",
                            name = "Margherita",
                            ingredients = "Tomato sauce, mozzarella, fresh basil, olive oil",
                            price = "$8.99",
                            productCategory = ProductCategory.DRINKS,
                            imageUrl = "",
                            toppings = emptyList()
                        ),
                        quantity = 0
                    ),
                    selectedItemQuantity = 2
                ),
                menuCardItem =  MenuCardItem(
                    productUi = ProductUi(
                        id = "123",
                        name = "Margherita",
                        ingredients = "Tomato sauce, mozzarella, fresh basil, olive oil",
                        price = "$8.99",
                        productCategory = ProductCategory.DRINKS,
                        imageUrl = "",
                        toppings = emptyList()
                    ),
                    quantity = 0
                )
            )
        }
    }
}