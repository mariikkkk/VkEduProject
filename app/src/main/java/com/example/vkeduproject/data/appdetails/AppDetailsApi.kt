package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.data.MockData
import kotlinx.coroutines.delay

class AppDetailsApi {
    suspend fun get(id:String): AppDetailsDto{
        delay(2000L)
         return MockData.apps.find { it.id == id } ?: throw IllegalStateException("Ничего не найдено")
    }
}