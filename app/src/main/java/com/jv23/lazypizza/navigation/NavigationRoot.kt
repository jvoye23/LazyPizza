package com.jv23.lazypizza.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.jv23.lazypizza.order_menu.presentation.product_detail.ProductDetailScreenRoot
import com.jv23.lazypizza.order_menu.presentation.product_menu.ProductMenuScreenRoot
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Serializable
data object HomeNavKey: NavKey

@Serializable
data class PizzaDetailNavKey(
    val productId: String? = null
): NavKey

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(HomeNavKey)

    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val directive = remember(windowAdaptiveInfo) {
        calculatePaneScaffoldDirective(windowAdaptiveInfo)
            .copy(horizontalPartitionSpacerSize = 24.dp)
    }
    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>(directive = directive)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        sceneStrategy = listDetailStrategy,
        entryProvider = { key ->
            when(key) {
                is HomeNavKey -> {
                    NavEntry(
                        key = HomeNavKey
                    ) {
                        ProductMenuScreenRoot(
                            viewModel = koinViewModel(),
                            onMenuItemClick = { productId ->
                                backStack.add(PizzaDetailNavKey(productId = productId))
                            }
                        )
                    }
                }
                is PizzaDetailNavKey -> {
                    NavEntry(
                        key = PizzaDetailNavKey(productId = key.productId)
                    ) {
                        ProductDetailScreenRoot(
                            onNavigateBackClick = { backStack.remove(key) },
                            viewModel = koinViewModel(
                                parameters = { parametersOf(key.productId) }
                            )
                        )
                    }
                }

                else -> throw RuntimeException("Invalid NavKey")
            }
        }
    )
}