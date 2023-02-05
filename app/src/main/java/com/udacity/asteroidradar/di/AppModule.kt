package com.udacity.asteroidradar.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.udacity.asteroidradar.db.AppDatabase
import com.udacity.asteroidradar.db.AsteroidDao
import com.udacity.asteroidradar.network.AsteroidService
import com.udacity.asteroidradar.vo.Asteroid
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

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "item_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAsteroidDao(database: AppDatabase): AsteroidDao {
        return database.asteroidDao()
    }

}