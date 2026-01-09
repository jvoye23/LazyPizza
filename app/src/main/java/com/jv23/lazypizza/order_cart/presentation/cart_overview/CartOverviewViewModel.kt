package com.jv23.lazypizza.order_cart.presentation.cart_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jv23.lazypizza.core.domain.model.OrderCart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class CartOverviewViewModel(
    private val orderCart: OrderCart
): ViewModel() {

    private val _state = MutableStateFlow(CartOverviewState())
    private var hasLoadedInitialData = false

    val state = _state
        .onStart {

            if (!hasLoadedInitialData) {
                //getCartItems()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            _state.value
        )

    fun onAction(action: CartOverviewAction) {
        when(action) {
            is CartOverviewAction.OnMinusClick -> {}
            is CartOverviewAction.OnPlusClick -> {}
            is CartOverviewAction.OnTrashBinClick -> {}
            else -> Unit
        }

    }
}