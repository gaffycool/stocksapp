package com.app.stockapp.ui.di

import com.app.commondata.repository.StocksRepositoryImpl
import com.app.commondomain.repository.StocksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindStocksRepository(
        analyticsServiceImpl: StocksRepositoryImpl
    ): StocksRepository
}
