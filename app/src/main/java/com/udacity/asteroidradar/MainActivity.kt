package com.udacity.asteroidradar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udacity.asteroidradar.di.AppComponent

class MainActivity : AppCompatActivity() {
    lateinit var appComponent: AppComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = (application as AsteroidRadarApplication).appComponent
        setContentView(R.layout.activity_main)
    }
}
