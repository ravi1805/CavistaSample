package com.cavista.sample.di.modules

import com.cavista.sample.presentation.SplashActivity
import com.cavista.sample.presentation.view.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeLoginActivity(): SearchActivity

}