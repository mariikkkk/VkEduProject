package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.domain.appdetails.Category

class CategoryMapper {
    fun toDomain(category: String): Category{
        return when (category){
            "App" -> Category.APP
            "Game" -> Category.GAME
            "Productivity" -> Category.PRODUCTIVITY
            "Social" -> Category.SOCIAL
            "Education" -> Category.EDUCATION
            "Entertainment" -> Category.ENTERTAINMENT
            "Music" -> Category.MUSIC
            "Video" -> Category.VIDEO
            "Photography" -> Category.PHOTOGRAPHY
            "Health" -> Category.HEALTH
            "Sports" -> Category.SPORTS
            "News" -> Category.NEWS
            "Books" -> Category.BOOKS
            "Business" -> Category.BUSINESS
            "Finance" -> Category.FINANCE
            "Travel" -> Category.TRAVEL
            "Maps" -> Category.MAPS
            "Food" -> Category.FOOD
            "Shopping" -> Category.SHOPPING
            "Utilities" -> Category.UTILITIES
            else -> throw IllegalStateException("Unsupported category type: $category")
        }
    }
}

