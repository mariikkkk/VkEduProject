package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.domain.appdetails.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor(){
    fun toDomain(category: String): Category{
        return when (category){
            "App", "Приложения" -> Category.APP
            "Game", "Игры" -> Category.GAME
            "Productivity", "Производительность" -> Category.PRODUCTIVITY
            "Social", "Социальные" -> Category.SOCIAL
            "Education", "Образование" -> Category.EDUCATION
            "Entertainment", "Развлечения" -> Category.ENTERTAINMENT
            "Music", "Музыка" -> Category.MUSIC
            "Video", "Видео" -> Category.VIDEO
            "Photography", "Фотография" -> Category.PHOTOGRAPHY
            "Health", "Здоровье" -> Category.HEALTH
            "Sports", "Спорт" -> Category.SPORTS
            "News", "Новости" -> Category.NEWS
            "Books", "Книги" -> Category.BOOKS
            "Business", "Бизнес" -> Category.BUSINESS
            "Finance", "Финансы" -> Category.FINANCE
            "Travel", "Путешествия" -> Category.TRAVEL
            "Maps", "Карты" -> Category.MAPS
            "Food", "Еда" -> Category.FOOD
            "Shopping", "Покупки" -> Category.SHOPPING
            "Utilities", "Инструменты" -> Category.UTILITIES
            else -> Category.APP
        }
    }
}

