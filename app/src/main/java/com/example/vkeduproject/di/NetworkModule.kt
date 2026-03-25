package com.example.vkeduproject.di

import com.example.vkeduproject.data.appdetails.AppDetailsApi
import com.example.vkeduproject.data.applist.AppListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl("http://185.103.109.134/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideAppListApi(retrofit: Retrofit): AppListApi {
        return retrofit.create(AppListApi::class.java)
    }
    @Provides
    @Singleton
    fun provideAppDetailsApi(retrofit: Retrofit): AppDetailsApi {
        return retrofit.create(AppDetailsApi::class.java)
    }
}