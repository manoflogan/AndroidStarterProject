package com.krishnanand.androidstarterproject.myproject.dagger

import com.krishnanand.androidstarterproject.myproject.main.MainActivity
import com.krishnanand.androidstarterproject.myproject.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity
}