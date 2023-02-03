package com.udacity.asteroidradar.repository

import com.google.gson.Gson
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
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
    private val asteroidService: AsteroidService
) {
    suspend fun getPictureOfDay(): PictureOfDay {
        return asteroidService.getPicOfDay()
    }

    suspend fun getAsteroidList(): ArrayList<Asteroid> {
        val sdf = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        val dateString = sdf.format(Date())
        val response =
            asteroidService.getAsteroidList(start = dateString, end = dateString)
        val gson = Gson()
        val jsonString = gson.toJson(response.body())
        return parseAsteroidsJsonResult(JSONObject(jsonString))
    }
}