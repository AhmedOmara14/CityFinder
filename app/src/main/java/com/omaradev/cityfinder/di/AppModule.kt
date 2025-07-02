package com.omaradev.cityfinder.di

import android.content.Context
import com.omaradev.cityfinder.data.repository.CityRepositoryImpl
import com.omaradev.cityfinder.domain.repository.CityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCityRepository(context: Context): CityRepository {
        return CityRepositoryImpl(context)
    }
}