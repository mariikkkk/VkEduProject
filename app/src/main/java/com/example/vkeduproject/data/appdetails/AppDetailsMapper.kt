package com.example.vkeduproject.data.appdetails

import com.example.vkeduproject.domain.appdetails.AppDetails
import javax.inject.Inject

class AppDetailsMapper @Inject constructor(
    private val categoryMapper: CategoryMapper
) {
    fun toDomain(dto: AppDetailsDto): AppDetails = AppDetails(
        id = dto.id,
        name = dto.name,
        developer = dto.developer,
        category = categoryMapper.toDomain(dto.category),
        ageRating = dto.ageRating,
        size = dto.size,
        iconUrl = dto.iconUrl,
        screenshotUrlList = dto.screenshotUrlList,
        description = dto.description
    )
}