package com.cavista.sample.domain.thread

import com.cavista.sample.domain.thread.IBackgroundThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class IBackgroundThreadExecutorImpl
@Inject constructor() : IBackgroundThreadExecutor {

    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        5, 10,
        20L, TimeUnit.SECONDS,
        LinkedBlockingQueue()
    )

    override fun execute(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }
}
