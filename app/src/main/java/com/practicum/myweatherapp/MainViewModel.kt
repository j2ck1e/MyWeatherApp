package com.practicum.myweatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<String>() // текущий день
    val liveDataList = MutableLiveData<List<String>>() // ближайшие дни
}