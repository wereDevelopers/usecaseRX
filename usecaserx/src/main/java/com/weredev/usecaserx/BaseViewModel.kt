package com.weredev.usecaserx

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weredev.usecaserx.utils.Resource


abstract class BaseViewModel : ViewModel() {


    private val disposableList = mutableListOf<BaseUseCase>()

    fun <T : Any, Y> SingleUseCase<T, Y>.executeAndDispose(
        mutableLiveData: MutableLiveData<Resource<T>>,
        params: Y? = null
    ) {
        if (!disposableList.contains(this)) {
            disposableList.add(this)
        }
        mutableLiveData.postValue(Resource.loading())
        this.execute(DefaultSingleObserver(mutableLiveData), params)
    }

    @CallSuper
    override fun onCleared() {
        disposableList.forEach { it.dispose() }
        disposableList.clear()
        super.onCleared()
    }


}