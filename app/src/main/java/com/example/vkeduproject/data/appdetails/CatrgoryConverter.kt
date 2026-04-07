package com.example.vkeduproject.data.appdetails

import androidx.room.TypeConverter
import com.example.vkeduproject.domain.appdetails.Category

class CatrgoryConverter {
    @TypeConverter
    fun fromCategory(category: Category): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(categoryName: String): Category{
        return Category.valueOf(categoryName)
    }
}