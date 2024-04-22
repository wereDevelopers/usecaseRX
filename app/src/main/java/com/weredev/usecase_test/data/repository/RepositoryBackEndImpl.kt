package com.weredev.usecase_test.data.repository

import com.weredev.usecase_test.domain.repository.RepositoryBackEnd
import kotlinx.coroutines.delay

class RepositoryBackEndImpl: RepositoryBackEnd {
    override fun getMessage(id: String): String {
        return when (id){
            "id" -> "Message OK"
            else -> "Message KO"
        }
    }
}