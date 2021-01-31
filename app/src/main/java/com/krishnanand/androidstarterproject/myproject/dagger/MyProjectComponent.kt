package com.krishnanand.androidstarterproject.myproject.dagger

import android.app.Application
import com.krishnanand.androidstarterproject.myproject.ProjectApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class
    ]
)
@Singleton
interface MyProjectComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): MyProjectComponent
    }

    fun inject(projectApplication: ProjectApplication)

}