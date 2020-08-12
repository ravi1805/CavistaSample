package com.cavista.sample.di.modules

import com.cavista.sample.domain.thread.IBackgroundThreadExecutor
import com.cavista.sample.domain.thread.IBackgroundThreadExecutorImpl
import com.cavista.sample.domain.thread.IUIThread
import com.cavista.sample.domain.thread.UIThreadImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExecuterModule {


    @Provides
    @Singleton
    internal fun provideThreadExecutor(threadExecuterImpl: IBackgroundThreadExecutorImpl): IBackgroundThreadExecutor {
        return threadExecuterImpl
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(iUiThreadImpl: UIThreadImpl): IUIThread {
        return iUiThreadImpl
    }


}
