package com.example.vkeduproject.data.applist

import com.example.vkeduproject.data.MockData
import com.example.vkeduproject.data.appdetails.AppDetailsDto
import com.example.vkeduproject.domain.appdetails.AppDetails
import com.example.vkeduproject.domain.appdetails.Category
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface AppListApi {
    @GET("catalog")
    suspend fun getCatalog(): List<AppDetailsDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}