package com.example.vkeduproject.domain.applist

import com.example.vkeduproject.domain.appdetails.AppDetails
import javax.inject.Inject

class GetAppListUseCase @Inject constructor(
    private val appListRepository: AppListRepository
) {
    suspend operator fun invoke(): List<AppDetails> {
        return appListRepository.getAppList()
    }
}
