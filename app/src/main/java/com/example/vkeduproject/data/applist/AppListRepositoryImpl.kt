package com.example.vkeduproject.data.applist

import com.example.vkeduproject.data.appdetails.AppDetailsMapper
import com.example.vkeduproject.domain.appdetails.AppDetails
import com.example.vkeduproject.domain.applist.AppListRepository
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor(
    private val mapper: AppDetailsMapper,
    private val api: AppListApi
): AppListRepository {
    override suspend fun getAppList(): List<AppDetails>{
        val dtoList = api.getAppList()
        return dtoList.map { mapper.toDomain(it) }

    }
}