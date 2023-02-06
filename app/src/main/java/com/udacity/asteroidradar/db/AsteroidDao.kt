package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.vo.Asteroid

@Dao
interface AsteroidDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(asteroids: List<Asteroid>)

    @Query("SELECT * FROM asteroid ORDER BY closeApproachDate")
    suspend fun getAll(): List<Asteroid>

    @Query("SELECT * FROM asteroid  WHERE closeApproachDate >= :start AND closeApproachDate <= :end ORDER BY closeApproachDate")
    suspend fun getDateRange(start: String, end: String): List<Asteroid>
}