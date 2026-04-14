package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.domain.appdetails.AppDetailRepository
import com.example.vkeduproject.domain.appdetails.AppDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
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

    override suspend fun toggleWishlist(id: String) {
        val currentEntity = dao.getAppDetails(id).first()
        currentEntity?.let {
            dao.updateWishlistStatus(id, !it.isInWishList)
        }
    }

    override fun observeAppDetails(id: String): Flow<AppDetails> {
        return dao.getAppDetails(id)
            .filterNotNull()
            .map { entityMapper.toDomain(it) }
    }
}