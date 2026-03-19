package com.example.vkeduproject.domain.appdetails

interface AppDetailRepository {
    suspend fun get(id: String): AppDetails
}