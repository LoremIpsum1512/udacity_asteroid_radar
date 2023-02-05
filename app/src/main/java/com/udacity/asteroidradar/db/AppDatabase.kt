package com.udacity.asteroidradar.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.vo.Asteroid

@Database(
    entities = [Asteroid::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun asteroidDao(): AsteroidDao
}