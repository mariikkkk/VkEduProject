package com.example.vkeduproject.presentation.applist

import com.example.vkeduproject.domain.appdetails.AppDetails

data class AppListState (
    val items: List<AppDetails> = emptyList()
)