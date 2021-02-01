package com.krishnanand.androidstarterproject.myproject.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class MainViewModel @Inject constructor(
    private val mainApi: MainApi
): ViewModel() {

    private val _stringLiveData: MutableLiveData<List<String>> = MutableLiveData()

    private val randomStringData: List<String> = listOf("My", "name", "is", "Kartik", "Krishnanand")

    val stringLiveData: LiveData<List<String>>
        get() = _stringLiveData

    fun makeAsyncRequest() {
        viewModelScope.launch {
            Random.nextInt(0, 2).apply {
                if (this == 0) {
                    _stringLiveData.postValue(null)
                } else {
                    _stringLiveData.postValue(randomStringData)
                }
            }
        }
    }
}