package com.krishnanand.androidstarterproject.myproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

/**
 * Injectable view model factory
 */
class InjectableViewModelFactory @Inject constructor(
    private val viewModelProviderMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModelProvider: Provider<ViewModel>? = viewModelProviderMap[modelClass]
        viewModelProvider ?: throw IllegalArgumentException("unable to find view model")
        return viewModelProvider.get() as T
    }
}