package com.weredev.usecaserx


import com.weredev.usecaserx.executor.JobThreadExecutor
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import com.weredev.usecaserx.executor.PostExecutionThread
import com.weredev.usecaserx.executor.ThreadExecutor

/**
 * [Single] use case class
 */
abstract class SingleUseCase<T : Any, in Params>(
    private val threadExecutor: ThreadExecutor = JobThreadExecutor.instance,
    private val postExecutionThread: PostExecutionThread = UIThread.instance
) : BaseUseCase() {

    /**
     * Builds a [Single] which will be used when executing the current [SingleUseCase].
     */
    protected abstract fun buildUseCaseSingle(params: Params?): Single<T>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableSingleObserver] which will be listening to the observable build
     * by [.buildUseCaseSingle] ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {
        launch {
            val observable = this.buildUseCaseSingle(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
            return@launch observable.subscribeWith(observer)
        }
    }
}
