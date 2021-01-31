package com.krishnanand.androidstarterproject.myproject.main

import retrofit2.Call
import retrofit2.http.GET

interface MainApi {

    @GET
    fun makeGetRequest(): Call<Any>
}