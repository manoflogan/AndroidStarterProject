package com.krishnanand.androidstarterproject.myproject.dagger

import android.app.Application
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Defines all the API rellated dependencies
 */
@Module
class ApiModule {

    @Singleton
    @Provides
    internal fun provideGson() = GsonBuilder().create()

    /**
     * Finds the 50MB size
     */
    @Singleton
    @Provides
    internal fun provideOkhttpCache(application: Application): Cache =
        Cache(File(application.cacheDir, "http-cache"),  50L * 1024L * 1024L)

    @Singleton
    @Provides
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .cache(cache)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://google.com")
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    internal fun provideSharedPreferences(application: Application) = PreferenceManager.getDefaultSharedPreferences(application)
}