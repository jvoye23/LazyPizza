package com.jv23.lazypizza.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jv23.lazypizza.core.domain.ProductRepository
import com.jv23.lazypizza.core.domain.model.MenuCardItem
import com.jv23.lazypizza.core.domain.model.ProductCategory
import com.jv23.lazypizza.core.presentation.mapper.toProductUi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
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


}