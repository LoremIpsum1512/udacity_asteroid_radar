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


    val asteroidList: LiveData<List<Asteroid>> = asteroidDao.getAll()

    suspend fun getPictureOfDay(): PictureOfDay? {
        return try {
            asteroidService.getPicOfDay()
        } catch (e: Exception) {
            null
        }


    }

    suspend fun refreshAsteroidList() {
        try {
            val sdf = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())

            val response =
                asteroidService.getAsteroidList(start = "", end = "")
            val gson = Gson()
            val jsonString = gson.toJson(response.body())
            val list = parseAsteroidsJsonResult(JSONObject(jsonString))
            asteroidDao.insertAll(list)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}