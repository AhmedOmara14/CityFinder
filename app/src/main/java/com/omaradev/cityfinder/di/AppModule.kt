package com.omaradev.cityfinder.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.omaradev.cityfinder.data.local.CityDao
import com.omaradev.cityfinder.data.local.CityDatabase
import com.omaradev.cityfinder.data.repository.CityRepositoryImpl
import com.omaradev.cityfinder.domain.repository.CityRepository
import com.omaradev.cityfinder.domain.usecase.GetAllCitiesUseCase
import com.omaradev.cityfinder.domain.usecase.SearchCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Context
    @Provides
    fun provideContext(application: Application): Context = application

    // Room Database
    @Provides
    @Singleton
    fun provideCityDatabase(app: Application): CityDatabase {
        return Room.databaseBuilder(app, CityDatabase::class.java, "city_db").build()
    }

    @Provides
    fun provideCityDao(db: CityDatabase): CityDao = db.cityDao()

    // SharedPreferences
    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("city_prefs", Context.MODE_PRIVATE)
    }

    // Repository
    @Provides
    @Singleton
    fun provideCityRepository(
        context: Context,
        dao: CityDao,
        sharedPreferences: SharedPreferences
    ): CityRepository {
        return CityRepositoryImpl(context, dao, sharedPreferences)
    }

    // Use Cases
    @Provides
    @Singleton
    fun provideSearchCityUseCase(repository: CityRepository): SearchCityUseCase {
        return SearchCityUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllCitiesUseCase(repository: CityRepository): GetAllCitiesUseCase {
        return GetAllCitiesUseCase(repository)
    }
}
