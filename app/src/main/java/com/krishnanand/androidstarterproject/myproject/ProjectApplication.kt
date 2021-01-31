package com.krishnanand.androidstarterproject.myproject

import android.app.Application
import com.krishnanand.androidstarterproject.myproject.dagger.DaggerMyProjectComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Starting point to the application.
 */
class ProjectApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        initApplication()
    }

    protected fun initApplication() {
        DaggerMyProjectComponent.builder().application(this).build().inject(this)
    }
}