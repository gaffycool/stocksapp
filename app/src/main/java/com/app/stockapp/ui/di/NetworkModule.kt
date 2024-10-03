package com.app.stockapp.ui.di

import com.app.commondata.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideUnsplashService(): com.app.commondata.api.ApiService {
        return com.app.commondata.api.ApiService.create()
    }
}
