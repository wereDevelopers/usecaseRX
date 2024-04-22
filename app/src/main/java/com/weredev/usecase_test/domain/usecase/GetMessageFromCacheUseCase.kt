package com.weredev.usecase_test.domain.usecaserx

import com.weredev.usecaserx.BaseSyncUseCase
import com.weredev.usecase_test.domain.repository.RepositoryCache

class GetMessageFromCacheUseCase(
    private val repositoryCache: RepositoryCache
): BaseSyncUseCase<String, String>() {
    override fun invoke(params: String?): String {
        return repositoryCache.getMessage(params ?: "")
    }
}