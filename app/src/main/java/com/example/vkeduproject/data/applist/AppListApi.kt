package com.example.vkeduproject.data.applist

import com.example.vkeduproject.data.MockData
import com.example.vkeduproject.data.appdetails.AppDetailsDto
import com.example.vkeduproject.domain.appdetails.AppDetails
import com.example.vkeduproject.domain.appdetails.Category

class AppListApi {
    suspend fun getAppList(): List<AppDetailsDto> {
        return MockData.apps
    }

}