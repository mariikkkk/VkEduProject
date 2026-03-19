package com.example.vkeduproject.presentation.applist

sealed interface ScreenEvent {
    data class ShowSnackbar(val message: String) : ScreenEvent
}