package com.example.vkeduproject.data.appdetails


data class AppDetailsDto (
    val id: String,
    val name: String,
    val developer: String,
    val category: String,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>?,
    val description: String,
)