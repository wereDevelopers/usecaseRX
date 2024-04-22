package com.weredev.usecaserx.executor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class JobThreadExecutor private constructor() : ThreadExecutor {
    private val threadPoolExecutor: ThreadPoolExecutor
    private val INITIAL_POOL_SIZE = 3
    private val MAX_POOL_SIZE = 5
    private val KEEP_ALIVE_TIME = 10L
    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS

    init {
        val workQueue = LinkedBlockingQueue<Runnable>()
        this.threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, workQueue)
    }

    override fun execute(runnable: Runnable?) {
        if (runnable == null) {
            throw IllegalArgumentException("Runnable to execute cannot be null")
        }
        this.threadPoolExecutor.execute(runnable)
    }

    private object Holder {
        val instance = JobThreadExecutor()
    }

    companion object {
        val instance: JobThreadExecutor by lazy { Holder.instance }
    }
}