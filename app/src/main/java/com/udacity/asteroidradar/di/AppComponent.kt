package com.udacity.asteroidradar.di

import com.udacity.asteroidradar.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(fragment: MainFragment)
}