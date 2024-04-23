package com.weredev.usecase_test.domain.usecase

import com.weredev.usecase_test.domain.repository.RepositoryCache
import com.weredev.usecaserx.CompletableUseCase
import io.reactivex.rxjava3.core.Completable

class DeleteItemToDB(
    private val repositoryCache: RepositoryCache
) : CompletableUseCase<String>() {
    override fun buildUseCaseCompletable(params: String?): Completable {
        return Completable.fromAction {
            Thread.sleep(1000)
            repositoryCache.deleteRow(params ?: "")
        }
    }
}