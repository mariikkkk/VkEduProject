package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.domain.appdetails.AppDetailRepository
import com.example.vkeduproject.domain.appdetails.AppDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val mapper: AppDetailsMapper,
    private val api: AppDetailsApi,
    private val dao: AppDetailsDao,
    private val entityMapper: AppDetailsEntityMapper
): AppDetailRepository {
    override suspend fun get(id: String): AppDetails {
        val entity = dao.getAppDetails(id).firstOrNull()

        return if (entity != null){
            entityMapper.toDomain(entity)
        } else {
            val dto = api.getAppDetails(id)
            val appDetails = mapper.toDomain(dto)
            val newEntity = entityMapper.toEntity(appDetails)

            withContext(Dispatchers.IO){
                dao.insertAppDetails(newEntity)
            }
            appDetails
        }
    }
}