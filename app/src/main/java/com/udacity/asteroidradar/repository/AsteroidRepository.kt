package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.gson.Gson
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.db.AsteroidDao
import com.udacity.asteroidradar.network.AsteroidService
import com.udacity.asteroidradar.utils.Constants
import com.udacity.asteroidradar.vo.Asteroid
import com.udacity.asteroidradar.vo.PictureOfDay
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class AsteroidRepository @Inject constructor(
    private val asteroidService: AsteroidService,
    private val asteroidDao: AsteroidDao
) {

    suspend fun getPictureOfDay(): PictureOfDay? {
        return try {
            asteroidService.getPicOfDay()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getAsteroidsFromNetwork(): List<Asteroid> {
        try {
            val response =
                asteroidService.getAsteroidList(start = "", end = "")
            val gson = Gson()
            val jsonString = gson.toJson(response.body())
            val list = parseAsteroidsJsonResult(JSONObject(jsonString))
            asteroidDao.insertAll(list)
            return list
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getAsteroidsFromDB(): List<Asteroid> {
        return asteroidDao.getAll()
    }

    suspend fun getAsteroidsFromDateRange(start: String, end: String): List<Asteroid> {
        return asteroidDao.getDateRange(start = start, end = end)
    }

}