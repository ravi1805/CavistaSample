package com.cavista.sample.di.modules

import android.app.Application
import com.cavista.sample.service.INetworkClientService
import com.cavista.sample.service.NetworkClientFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    /*
   * The method returns the network client object
   * */
    @Singleton
    @Provides
    fun provideRetrofitNetworkService(application: Application): INetworkClientService {
        return NetworkClientFactory(application)
    }


}