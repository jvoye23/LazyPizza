package com.jv23.lazypizza.order_menu.presentation.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jv23.lazypizza.core.domain.ProductRepository
import com.jv23.lazypizza.core.presentation.mapper.toToppingUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val productId: String
): ViewModel() {

    private val _state = MutableStateFlow(ProductDetailState())
    private var hasLoadedInitialData = false

    val state = _state
        .onStart {

            if (!hasLoadedInitialData) {
                getProductFromRepo(productId)
                getToppingsFromRepo()

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
            is ProductDetailAction.OnMinusClick -> {decreaseQuantity(action.pizzaToppingItem)}
            is ProductDetailAction.OnPlusClick -> {addQuantity(action.pizzaToppingItem)}
            is ProductDetailAction.OnPizzaToppingCardClick -> { onPizzaToppingCardClick(action.pizzaToppingItem) }
            else -> Unit

        }
    }

    private fun addQuantity(pizzaToppingItem: PizzaTopping) {
        val itemPrice = pizzaToppingItem.toppingUi.price

        _state.update { currentState ->
            val updatedToppings = currentState.pizzaToppings.map { item ->
                if (item.toppingUi.id == pizzaToppingItem.toppingUi.id) {
                    val newQuantity = item.quantity + 1
                    item.copy(
                        quantity = newQuantity
                    )
                } else {
                    item
                }
            }
            currentState.copy(
                pizzaToppings = updatedToppings,
                totalCartValue = currentState.totalCartValue + itemPrice
            )
        }
    }

    private fun decreaseQuantity(pizzaToppingItem: PizzaTopping) {
        val itemPrice = pizzaToppingItem.toppingUi.price
        _state.update { currentState ->
            val updatedToppings = currentState.pizzaToppings.map { item ->
                if (item.toppingUi.id == pizzaToppingItem.toppingUi.id) {
                    val newQuantity = (item.quantity - 1).coerceAtLeast(0)
                    item.copy(
                        quantity = newQuantity,
                        isSelected = newQuantity > 0
                    )
                } else {
                    item
                }
            }
            currentState.copy(
                pizzaToppings = updatedToppings,
                totalCartValue = currentState.totalCartValue - itemPrice
            )
        }
    }

    private fun onPizzaToppingCardClick(pizzaToppingItem: PizzaTopping) {
        val itemPrice = pizzaToppingItem.toppingUi.price
        _state.update { currentState ->
            // 1. Iterate efficiently over the list
            val updatedToppings = currentState.pizzaToppings.map { item ->
                // 2. Find the item to change
                if (item.toppingUi.id == pizzaToppingItem.toppingUi.id) {
                    // 3. Create a COPY with the new value (Immutability)
                    // If it was selected, unselect it. If unselected, select it.
                    item.copy(
                        isSelected = !item.isSelected,
                        quantity = if (item.quantity == 0) 1 else item.quantity
                    )
                } else {
                    // 4. Important: Return the EXACT SAME instance for unchanged items.
                    // This ensures Compose knows these items didn't change and skips recomposition for them.
                    item
                }
            }
            currentState.copy(
                pizzaToppings = updatedToppings,
                totalCartValue = currentState.totalCartValue + itemPrice
            )
        }
    }

    private suspend fun getProductFromRepo(productId: String) {
        val productItem = productRepository.getProductById(productId)
        val price = productItem?.price
        _state.update { it.copy(
            productUi = productItem,
            totalCartValue = price ?: 0
        ) }
    }

    private fun getToppingsFromRepo() {
        viewModelScope.launch {
            productRepository.getToppingItems()
                .collect { toppings ->
                    _state.update {
                        it.copy(
                            pizzaToppings = toppings.map { topping ->
                                PizzaTopping(
                                    toppingUi = topping.toToppingUi(),
                                    isSelected = false,
                                    quantity = 0
                                )
                            }
                        )
                    }
                }
        }
    }

}