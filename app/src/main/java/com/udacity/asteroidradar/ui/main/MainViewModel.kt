package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.utils.Constants
import com.udacity.asteroidradar.vo.Asteroid
import com.udacity.asteroidradar.vo.PictureOfDay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val asteroidRepository: AsteroidRepository
) : ViewModel() {
    private val _picOfDay = MutableLiveData<PictureOfDay?>()
    val picOfDay: LiveData<PictureOfDay?>
        get() = _picOfDay

    private val _asteroidList = MutableLiveData<List<Asteroid>>()
    val asteroidList: LiveData<List<Asteroid>>
        get() = _asteroidList

    init {
        getAll()
    }

    fun getPictureOfDay() {
        viewModelScope.launch {
            val pic = asteroidRepository.getPictureOfDay()
            if (pic != null) _picOfDay.value = pic
        }
    }

    fun getAll() {
        viewModelScope.launch {
            _asteroidList.value = asteroidRepository.getAsteroidsFromDB()
        }
    }

    fun refreshAsteroidList() {
        viewModelScope.launch {
            try {
                _asteroidList.value = asteroidRepository.getAsteroidsFromNetwork()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getAsteroidFromDateRange(range: DateRange) {
        viewModelScope.launch {
            val current = Date()


            val formater = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT)
            var start = formater.format(current)

            var end = ""

            when (range) {
                DateRange.today -> end = start
                DateRange.week -> {
                    val calendar = Calendar.getInstance()
                    calendar.time = current
                    calendar.add(Calendar.DATE, 7)
                    end = formater.format(calendar.time)
                }
            }

            try {
                _asteroidList.value = asteroidRepository.getAsteroidsFromDateRange(
                    start, end
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

enum class DateRange {
    week, today,
}