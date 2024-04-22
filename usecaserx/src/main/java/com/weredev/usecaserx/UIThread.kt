package com.weredev.usecaserx

import com.weredev.usecaserx.executor.PostExecutionThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

class UIThread private constructor() : PostExecutionThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    private object Holder {
        val instance = UIThread()
    }

    companion object {
        val instance: PostExecutionThread by lazy { Holder.instance }
    }
}
