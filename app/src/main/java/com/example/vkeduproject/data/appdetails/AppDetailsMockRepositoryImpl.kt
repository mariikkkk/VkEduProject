package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.domain.appdetails.AppDetailRepository
import com.example.vkeduproject.domain.appdetails.AppDetails
import javax.inject.Inject

class AppDetailsMockRepositoryImpl @Inject constructor(
    private val mapper: AppDetailsMapper,
    private val api: AppDetailsApi
): AppDetailRepository {
    override suspend fun get(id: String): AppDetails {
        val dto = api.get(id)
        val domain = mapper.toDomain(dto)
        return domain
    }
}
