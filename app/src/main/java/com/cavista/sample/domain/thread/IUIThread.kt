package com.cavista.sample.domain.thread

import io.reactivex.Scheduler

interface IUIThread {
    fun getMainThread(): Scheduler
}
