package com.jv23.lazypizza.order_menu.presentation.product_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jv23.lazypizza.core.domain.ProductRepository
import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.presentation.mapper.toProductUi
import com.jv23.lazypizza.order_menu.presentation.util.textAsFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductMenuViewModel(
    private val productRepository: ProductRepository
): ViewModel() {

    private val _state = MutableStateFlow(ProductMenuState())
    private var hasLoadedInitialData = false

    val state = _state
        .onStart {

            if (!hasLoadedInitialData) {
                productRepository.fetchProductCatalog()
                getProducts()
                searchProduct()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            _state.value
        )

    private fun getProducts() {
        viewModelScope.launch {
            productRepository.getProductItems()
                .collect { products ->
                    /*_state.update { it ->
                        it.copy(
                            products = products.map { it.toProductUi() }
                        )
                    }*/
                    _state.update { it ->
                        it.copy(
                            menuCardItems = products.map {
                                MenuCardItem(
                                    productUi = it.toProductUi(),
                                    quantity = 0
                                )
                            },
                            dataSource = products.map {
                                MenuCardItem(
                                    productUi = it.toProductUi(),
                                    quantity = 0
                                )
                            }
                        )
                    }
                }
        }
    }


    fun onAction(action: ProductMenuAction) {
        when(action) {
            is ProductMenuAction.OnMenuItemCardClick -> {}
            is ProductMenuAction.OnChangeProductCategoryFilter -> {
                _state.update { it.copy(
                    categoryFilter = action.categoryFilter
                ) }
            }
        }
    }

    fun searchProduct() {
        _state.value.searchInput.textAsFlow()
            .onEach { input ->
                val searchedItems = state.value.dataSource
                    .filter { it.productUi.name.contains(input, ignoreCase = true) }
                _state.update { it.copy(
                    menuCardItems = searchedItems
            )}
            }
            .launchIn(viewModelScope)
    }
}

