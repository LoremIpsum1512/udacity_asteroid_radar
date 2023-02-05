package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.di.AppComponent
import com.udacity.asteroidradar.di.DaggerAppComponent

class AsteroidRadarApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}