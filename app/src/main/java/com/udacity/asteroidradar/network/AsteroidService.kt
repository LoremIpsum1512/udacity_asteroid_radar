package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.vo.PictureOfDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {
    @GET("/planetary/apod")
    suspend fun getPicOfDay(): PictureOfDay

    @GET("/neo/rest/v1/feed")
    suspend fun getAsteroidList(
        @Query("start_date") start: String,
        @Query("end_date") end: String
    ): Response<Any>
}
