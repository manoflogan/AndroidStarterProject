package com.krishnanand.androidstarterproject.myproject.main

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainApiModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit) = retrofit.create(MainApi::class.java)

}