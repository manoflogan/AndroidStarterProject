package com.krishnanand.androidstarterproject.myproject.viewmodels

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Binds the injection factory
 */
@Module
interface ViewModelInjectionModule {

    @Binds
    fun bindInjectableViewModelFactory(injectableViewModelFactory: InjectableViewModelFactory): ViewModelProvider.Factory
}