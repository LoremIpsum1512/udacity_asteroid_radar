package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.utils.Constants
import com.udacity.asteroidradar.vo.Asteroid
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val asteroidRepository: AsteroidRepository
) : ViewModel() {
    private val _picOfDay = MutableLiveData<String>()
    val picOfDay: LiveData<String>
        get() = _picOfDay

    val asteroidList: LiveData<List<Asteroid>>
        get() = asteroidRepository.asteroidList

    fun getPictureOfDay() {
        viewModelScope.launch {
            val pic = asteroidRepository.getPictureOfDay()
            if (pic != null)
                _picOfDay.value = pic.url
        }
    }

    fun refreshAsteroidList() {
        viewModelScope.launch {
            asteroidRepository.refreshAsteroidList()
        }
    }

}