package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.di.AppComponent
import com.udacity.asteroidradar.di.DaggerAppComponent

class AsteroidRadarApplication : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}