package com.udacity.asteroidradar.di

import android.content.Context
import com.udacity.asteroidradar.AsteroidRadarApplication
import com.udacity.asteroidradar.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: AsteroidRadarApplication)
    fun inject(fragment: MainFragment)
}