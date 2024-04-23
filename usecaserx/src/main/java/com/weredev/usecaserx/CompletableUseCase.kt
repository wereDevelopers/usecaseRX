package com.weredev.usecaserx


import com.weredev.usecaserx.executor.JobThreadExecutor
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import com.weredev.usecaserx.executor.PostExecutionThread
import com.weredev.usecaserx.executor.ThreadExecutor
import io.reactivex.rxjava3.core.Completable

/**
 * [Completable] use case class, use for operation which doesn't return any value (es. delete row on DB)
 */
abstract class CompletableUseCase<in Params>(
    private val threadExecutor: ThreadExecutor = JobThreadExecutor.instance,
    private val postExecutionThread: PostExecutionThread = UIThread.instance
) : BaseUseCase() {

    /**
     * Builds a [Completable] which will be used when executing the current [CompletableUseCase].
     */
    protected abstract fun buildUseCaseCompletable(params: Params?): Completable

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableCompletableObserver] which will be listening to the observable build
     * by [.buildUseCaseCompletable] ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(observer: DefaultCompletableObserver, params: Params? = null) {
        launch {
            val observable = this.buildUseCaseCompletable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
            return@launch observable.subscribeWith(observer)
        }
    }
}
