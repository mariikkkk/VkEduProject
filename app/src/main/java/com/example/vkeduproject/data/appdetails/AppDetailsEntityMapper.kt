package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.domain.appdetails.AppDetails
import javax.inject.Inject

class AppDetailsEntityMapper @Inject constructor(){
    fun toDomain(entity: AppDetailsEntity): AppDetails{
        val res = AppDetails(
            id = entity.id,
            name = entity.name,
            developer = entity.developer,
            category = entity.category,
            ageRating = entity.ageRating,
            size = entity.size,
            iconUrl = entity.iconUrl,
            screenshotUrlList = emptyList(),
            description = entity.description
        )
        return res
    }

    fun toEntity(appDetails: AppDetails): AppDetailsEntity{
        val res = AppDetailsEntity(
            id = appDetails.id,
            name = appDetails.name,
            developer = appDetails.developer,
            category = appDetails.category,
            ageRating = appDetails.ageRating,
            size = appDetails.size,
            iconUrl = appDetails.iconUrl,
            description = appDetails.description
        )
        return res
    }
}