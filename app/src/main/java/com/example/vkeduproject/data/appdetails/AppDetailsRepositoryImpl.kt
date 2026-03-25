package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.domain.appdetails.AppDetailRepository
import com.example.vkeduproject.domain.appdetails.AppDetails
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val mapper: AppDetailsMapper,
    private val api: AppDetailsApi
): AppDetailRepository {
    override suspend fun get(id: String): AppDetails {
        val dto = api.getAppDetails(id)
        return mapper.toDomain(dto)
    }
}