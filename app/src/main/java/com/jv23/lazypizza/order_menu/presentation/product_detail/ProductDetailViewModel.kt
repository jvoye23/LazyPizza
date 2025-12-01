package com.jv23.lazypizza.order_menu.presentation.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jv23.lazypizza.core.domain.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val productId: String
): ViewModel() {

    private val _state = MutableStateFlow(ProductDetailState())
    private var hasLoadedInitialData = false

    val state = _state
        .onStart {

            if (!hasLoadedInitialData) {
                //productRepository.getProductById(productId)

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            _state.value
        )

    fun onAction(action: ProductDetailAction) {
        when(action) {
            ProductDetailAction.OnClickAddToCart -> {}
            ProductDetailAction.OnClickMinus -> {}
        }
    }

}