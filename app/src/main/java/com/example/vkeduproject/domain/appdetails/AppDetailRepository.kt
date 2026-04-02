package com.example.vkeduproject.domain.appdetails

import kotlinx.coroutines.flow.Flow

interface AppDetailRepository {
    suspend fun get(id: String): AppDetails
    suspend fun toggleWishlist(id: String)
    fun observeAppDetails(id: String): Flow<AppDetails>
}