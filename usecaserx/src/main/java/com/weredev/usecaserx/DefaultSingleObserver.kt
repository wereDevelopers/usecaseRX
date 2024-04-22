package com.weredev.usecaserx

import androidx.lifecycle.MutableLiveData
import com.weredev.usecaserx.utils.Resource
import io.reactivex.rxjava3.observers.DisposableSingleObserver

open class DefaultSingleObserver<T>(private val mutableLiveData: MutableLiveData<Resource<T>>? = null)
    : DisposableSingleObserver<T>() {
    override fun onSuccess(t: T) {
        mutableLiveData?.postValue(Resource.success(t))
    }
    override fun onError(e: Throwable) {
        mutableLiveData?.postValue(Resource.error(e))
    }
}