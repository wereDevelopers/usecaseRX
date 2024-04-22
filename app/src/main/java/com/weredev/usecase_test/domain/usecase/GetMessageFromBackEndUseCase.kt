package com.weredev.usecase_test.domain.usecaserx

import com.weredev.usecaserx.SingleUseCase
import com.weredev.usecase_test.domain.repository.RepositoryBackEnd
import io.reactivex.rxjava3.core.Single

class GetMessageFromBackEndUseCase(
    private val repositoryBackEnd: RepositoryBackEnd
): SingleUseCase<String, String>() {

    override fun buildUseCaseSingle(params: String?): Single<String> {
        return Single.create { emitter ->
            emitter.onSuccess(repositoryBackEnd.getMessage(params ?: ""))
        }

    }

}