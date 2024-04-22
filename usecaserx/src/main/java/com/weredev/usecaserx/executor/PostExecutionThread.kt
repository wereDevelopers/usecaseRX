package com.weredev.usecaserx.executor

import io.reactivex.rxjava3.core.Scheduler

interface PostExecutionThread {
    fun getScheduler() : Scheduler
}