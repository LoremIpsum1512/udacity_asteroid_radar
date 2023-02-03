package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.network.Network
import com.udacity.asteroidradar.utils.Constants
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

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

    fun getAsteroidList() {
        viewModelScope.launch {
            val sdf = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            val dateString = sdf.format(Date())
            val response =
                Network.asteroidService.getAsteroidList(start = dateString, end = dateString)
            val gson = Gson()
            val jsonString = gson.toJson(response.body())
            val asteroidList = parseAsteroidsJsonResult(JSONObject(jsonString))
        }
    }

}