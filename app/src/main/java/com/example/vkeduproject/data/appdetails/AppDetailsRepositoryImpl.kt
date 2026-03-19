package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.domain.appdetails.AppDetailRepository
import com.example.vkeduproject.domain.appdetails.AppDetails

class AppDetailsRepositoryImpl(
    private val mapper: AppDetailsMapper,
    private val api: AppDetailsApi
): AppDetailRepository {
    override suspend fun get(id: String): AppDetails {
        TODO()
    }

}