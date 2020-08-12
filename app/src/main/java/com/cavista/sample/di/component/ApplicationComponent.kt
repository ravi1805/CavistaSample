package com.cavista.sample.di.component

import android.app.Application
import com.cavista.sample.MainApplication
import com.cavista.sample.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


/*
 * We mark this class with the @Component annotation.
 * and we define all the modules that can be injected.
 * Note that we provide AndroidSupportInjectionModule.class
 * here. This class was not created by us.
 * It is an internal class in Dagger 2.10.
 * Provides our activities and fragments with given module.
 * */


@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModuleDI::class,
        NetworkModule::class,
        ExecuterModule::class,
        RepositoryModule::class,
        UserPreferenceModule::class]
)

interface ApplicationComponent {

    /*
    * We will call this builder interface from our project custom Application class.
    * This will set our application object to the AppComponent.
    * So inside the AppComponent the application instance is available.
    * So this application instance can be accessed by our modules
    * such as ApiModule when needed
    *
    * */

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }


    fun inject(application: MainApplication)
}
