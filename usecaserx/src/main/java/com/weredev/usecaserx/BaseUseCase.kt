package com.weredev.usecaserx

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


abstract class BaseUseCase {

    private val disposables = CompositeDisposable()

    fun launch(job: () -> Disposable) {
        disposables.add(job())
    }

    open fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

}