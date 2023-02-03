package com.udacity.asteroidradar.di

import com.udacity.asteroidradar.network.AsteroidService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideAsteroidService(retrofit: Retrofit): AsteroidService {
        return retrofit.create(AsteroidService::class.java)
    }
}