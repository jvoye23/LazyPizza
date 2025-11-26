package com.jv23.lazypizza.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jv23.lazypizza.core.domain.ProductRepository
import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.presentation.mapper.toProductUi
import com.jv23.lazypizza.home.presentation.util.textAsFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val productRepository: ProductRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
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
                            }
                        )
                    }
                }
        }
    }


    fun onAction(action: HomeAction) {
        when(action) {
            is HomeAction.OnMenuItemCardClick -> {}
            is HomeAction.OnChangeProductCategoryFilter -> {
                _state.update { it.copy(
                    categoryFilter = action.categoryFilter
                ) }
            }
        }
    }

    fun searchProduct() {

        _state.value.searchInput.textAsFlow()
            .onEach { input ->
                _state.update { currentState ->
                    val filtered = if (input.isBlank()) {
                        // Restore from the backup list
                        currentState.menuCardItems
                    } else {
                        // Filter the backup list, NOT the visible list
                        currentState.menuCardItems.filter {
                            it.productUi.name.contains(input, ignoreCase = true)
                        }
                    }

                    currentState.copy(menuCardItems = filtered)
                }
            }
            .launchIn(viewModelScope)

        /*val allProducts = state.value.menuCardItems

        _state.value.searchInput.textAsFlow()
            .onEach { input ->
                _state.update { it.copy(
                    menuCardItems = allProducts
                ) }

                val searchedItems = state.value.menuCardItems
                    .filter { it.productUi.name.contains(input, ignoreCase = true) }

                _state.update { it.copy(
                    menuCardItems = searchedItems
                )}

            }
            .launchIn(viewModelScope)*/

    }

}

//val pizzas =  state.menuCardItems
//                        .filter { it.productUi.productCategory == ProductCategory.PIZZA }