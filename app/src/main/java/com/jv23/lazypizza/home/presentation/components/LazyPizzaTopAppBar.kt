package com.jv23.lazypizza.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.lazypizza.R
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_Phone
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_PhoneFilled
import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_Pizza

import com.jv23.lazypizza.core.presentation.designsystem.theme.Icon_Plus
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import com.jv23.lazypizza.core.presentation.designsystem.theme.bg
import com.jv23.lazypizza.core.presentation.designsystem.theme.body1Regular
import com.jv23.lazypizza.core.presentation.designsystem.theme.body3Bold
import com.jv23.lazypizza.core.presentation.designsystem.theme.body3Regular
import com.jv23.lazypizza.core.presentation.designsystem.theme.textPrimary
import com.jv23.lazypizza.core.presentation.designsystem.theme.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyPizzaTopAppBar(
    modifier: Modifier = Modifier
) {

    TopAppBar(
        title = {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = Icon_Pizza,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "LazyPizza",
                    style = MaterialTheme.typography.body3Bold,
                    color = MaterialTheme.colorScheme.textPrimary
                )


            }
        },
        actions = {
            Row(
                modifier = Modifier
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier
                        .size(14.dp),
                    imageVector = Icon_PhoneFilled,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.textSecondary

                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "+1 (555) 321-7890",
                    style = MaterialTheme.typography.body1Regular,
                    color = MaterialTheme.colorScheme.textPrimary
                )

            }
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.bg,
            scrolledContainerColor = MaterialTheme.colorScheme.bg,
            navigationIconContentColor = MaterialTheme.colorScheme.textSecondary,
            titleContentColor = MaterialTheme.colorScheme.textPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.textSecondary,
            subtitleContentColor = MaterialTheme.colorScheme.textPrimary
        ),
    )
}

@Preview (showSystemUi = true, showBackground = true)
@Composable
private fun LazyPizzaTopAppBarPreview() {
    LazyPizzaTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            LazyPizzaTopAppBar()
        }
    }

}