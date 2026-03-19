package com.example.vkeduproject.domain.applist

import com.example.vkeduproject.domain.appdetails.AppDetails

class GetAppListUseCase(
    private val appListRepository: AppListRepository
) {
    suspend operator fun invoke(): List<AppDetails> {
        return appListRepository.getAppList()
    }
}
