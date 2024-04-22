package com.weredev.usecase_test.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weredev.usecaserx.BaseViewModel
import com.weredev.usecaserx.utils.Resource
import com.weredev.usecase_test.data.repository.RepositoryBackEndImpl
import com.weredev.usecase_test.data.repository.RepositoryCacheImpl
import com.weredev.usecase_test.domain.repository.RepositoryCache
import com.weredev.usecase_test.domain.usecaserx.GetMessageFromBackEndUseCase
import com.weredev.usecase_test.domain.usecaserx.GetMessageFromCacheUseCase

class HomeViewModel : BaseViewModel() {
    private val repoCache = RepositoryCacheImpl()
    private val repoBE = RepositoryBackEndImpl()
    private val getMessageFromBackEndUseCase = GetMessageFromBackEndUseCase(repoBE)
    private val getMessageFromCacheUseCase = GetMessageFromCacheUseCase(repoCache)

    val messageBELiveData: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getMessageFromBackEnd(id: String) {
        getMessageFromBackEndUseCase.executeAndDispose(messageBELiveData, id)
    }

    fun getMessageCache(id: String) {
        getMessageFromCacheUseCase.invoke(id)
    }
}