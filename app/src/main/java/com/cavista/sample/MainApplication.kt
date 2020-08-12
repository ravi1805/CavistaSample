package com.cavista.sample

import androidx.multidex.MultiDexApplication
import com.cavista.sample.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Android Main Application
 */
class MainApplication : MultiDexApplication() , HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

}
