package com.example.vkeduproject.data.appdetails

import kotlinx.serialization.Serializable

@Serializable
data class AppDetailsDto (
    val id: String,
    val name: String,
    val developer: String = "",
    val category: String,
    val ageRating: Int = 0,
    val size: Float = 0f,
    val iconUrl: String,
    val screenshotUrlList: List<String>? = emptyList(),
    val description: String,
)