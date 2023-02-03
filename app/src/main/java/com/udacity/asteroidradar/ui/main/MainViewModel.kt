package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.network.Network
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _picOfDay = MutableLiveData<String>()
    val picOfDay: LiveData<String>
        get() = _picOfDay

    fun getPictureOfDay() {
        viewModelScope.launch {
            val pic = Network.asteroidService.getPicOfDay()
            _picOfDay.value = pic.url
        }
    }
}