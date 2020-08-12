package com.cavista.sample.di.modules

import com.cavista.sample.data.datasource.IRemoteDataTransaction
import com.cavista.sample.data.preferences.UserPreference
import com.cavista.sample.data.remote.RemoteTransactionManager
import com.cavista.sample.data.repository.DataRepoImpl
import com.cavista.sample.domain.repository.IDataRepo
import com.cavista.sample.service.INetworkClientService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteDataRepo(
        networkService: INetworkClientService
    ): IRemoteDataTransaction {
        return RemoteTransactionManager(networkService)
    }


    @Provides
    @Singleton
    fun provideDataRepo(
        iRemoteDataTransaction: IRemoteDataTransaction,
        userPref: UserPreference
    ): IDataRepo {
        return DataRepoImpl(iRemoteDataTransaction, userPref)
    }

}