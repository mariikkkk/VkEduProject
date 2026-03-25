package com.example.vkeduproject.di

import com.example.vkeduproject.data.appdetails.AppDetailsRepositoryImpl
import com.example.vkeduproject.data.applist.AppListRepositoryImpl
import com.example.vkeduproject.domain.appdetails.AppDetailRepository
import com.example.vkeduproject.domain.applist.AppListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppListRepository(
        impl: AppListRepositoryImpl
    ): AppListRepository

    @Binds
    abstract fun bindAppDetailRepository(
        impl: AppDetailsRepositoryImpl
    ): AppDetailRepository

}