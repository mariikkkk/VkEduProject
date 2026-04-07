package com.example.vkeduproject

import com.example.vkeduproject.data.appdetails.AppDetailsEntity
import com.example.vkeduproject.data.appdetails.AppDetailsEntityMapper
import com.example.vkeduproject.data.appdetails.CategoryMapper
import com.example.vkeduproject.domain.appdetails.AppDetails
import com.example.vkeduproject.domain.appdetails.Category
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MappersTest {
    val categoryMapper = CategoryMapper()
    val appDetailsEntityMapper = AppDetailsEntityMapper()

    @Test
    fun `Игры EXPECT Category GAME`(){
        val res = categoryMapper.toDomain("Игры")
        assertEquals(Category.GAME, res)
    }

    @Test
    fun `Неизвестное EXPECT Category APP`(){
        val res = categoryMapper.toDomain("Супер пупер дупер приложение")
        assertEquals(Category.APP, res)
    }

    @Test
    fun `Entity EXPECT mapped Domain`(){
        val res = appDetailsEntityMapper.toDomain(
            AppDetailsEntity(
                "1", "Приложение", "Kulikov", Category.BUSINESS,
                2, 3f,  "", "Классное приложение", true
            )
        )
        assertEquals("1", res.id)
        assertEquals(2, res.ageRating)
    }

    @Test
    fun `Domain EXPECT mapped Entity`(){
        val res = appDetailsEntityMapper.toEntity(
            AppDetails(
                "2", "Возможно приложение", "Kubikov", Category.HEALTH,
                18, 10f, "", emptyList(),"Не для детей",
                false
            )
        )
        assertEquals(Category.HEALTH, res.category)
        assertEquals("Не для детей", res.description)
    }
}