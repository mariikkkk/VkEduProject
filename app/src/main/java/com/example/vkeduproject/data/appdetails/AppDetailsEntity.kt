package com.example.vkeduproject.data.appdetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vkeduproject.domain.appdetails.Category

@Entity(tableName = "app_details")
data class AppDetailsEntity (
    @PrimaryKey val id: String,
    val name: String,
    val developer: String,
    val category: Category,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val description: String,
    val isInWishList: Boolean = false
)