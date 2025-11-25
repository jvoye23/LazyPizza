package com.jv23.lazypizza.home.presentation

sealed interface HomeEvent {
    data object OnSelected: HomeEvent
}