package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.vo.PictureOfDay
import retrofit2.http.GET

interface AsteroidService {
    @GET("/planetary/apod")
    suspend fun getPicOfDay(): PictureOfDay
}
