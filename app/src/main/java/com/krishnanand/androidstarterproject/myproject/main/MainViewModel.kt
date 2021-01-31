package com.krishnanand.androidstarterproject.myproject.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainApi: MainApi
): ViewModel() {

    fun makeAsyncRequest() {
        viewModelScope.launch {
        }
    }
}