package com.udacity.asteroidradar.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//object Network {
//
//
//    private val client = OkHttpClient.Builder()
//        .addNetworkInterceptor(NasaKeyInterceptor())
//        .build()
//
//    private val moshi: Moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()
//
//
//    private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .client(client)
//        .build()
//
//    val asteroidService: AsteroidService = retrofit.create(AsteroidService::class.java)
//}


class NasaKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(
            "api_key",
            Constants.API_KEY
        ).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}