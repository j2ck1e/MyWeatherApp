package com.practicum.myweatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.myweatherapp.adapters.WeatherInfo

class MainViewModel : ViewModel() {

    val liveDataCurrent = MutableLiveData<WeatherInfo>() // текущий день (информацию будем брать из датаКласса)
    val liveDataList = MutableLiveData<List<WeatherInfo>>() // ближайшие дни
}