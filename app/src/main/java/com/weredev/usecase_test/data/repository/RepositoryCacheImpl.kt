package com.weredev.usecase_test.data.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.weredev.usecase_test.domain.repository.RepositoryCache

class RepositoryCacheImpl: RepositoryCache {
    override fun getMessage(id: String): String {
        return when (id){
            "id" -> "Message OK"
            else -> "Message KO"
        }
    }

    override fun deleteRow(name: String) {
        throw Exception("error on delete item: $name")
        //Log.d("RepositoryCacheImpl", "delete item $name")
    }
}