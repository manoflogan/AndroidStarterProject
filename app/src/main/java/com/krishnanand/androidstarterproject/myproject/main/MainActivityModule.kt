package com.krishnanand.androidstarterproject.myproject.main

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.krishnanand.androidstarterproject.myproject.viewmodels.ViewModelInjectionModule
import com.krishnanand.androidstarterproject.myproject.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelInjectionModule::class,
        MainApiModule::class
    ]
)
interface MainActivityModule {

    @Binds
    fun bindMainActivity(activity: MainActivity): AppCompatActivity

    @[Binds ViewModelKey(MainViewModel::class) IntoMap]
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}