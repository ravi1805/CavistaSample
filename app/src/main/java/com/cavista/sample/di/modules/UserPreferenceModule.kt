package com.cavista.sample.di.modules

import android.app.Application
import com.cavista.sample.data.preferences.UserPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserPreferenceModule {
    @Provides
    @Singleton
    fun providesUserPreference(application: Application): UserPreference {
        return UserPreference(application)
    }
}