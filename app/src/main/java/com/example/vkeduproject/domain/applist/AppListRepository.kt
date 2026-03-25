package com.example.vkeduproject.domain.applist

import com.example.vkeduproject.domain.appdetails.AppDetails

interface AppListRepository{
    suspend fun getAppList(): List<AppDetails>
}