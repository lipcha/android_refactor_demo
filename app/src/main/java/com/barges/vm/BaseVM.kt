package com.barges.vm

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barges.loading_manager.LoadingManagerImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.util.*
import kotlin.coroutines.coroutineContext

class BaseVM : ViewModel(), DefaultLifecycleObserver{

    private val tag = UUID.randomUUID().toString()

    private val loadingManager = LoadingManagerImpl(viewModelScope)

    suspend fun withLoading() {
        val uuid = UUID.randomUUID().toString()
        loadingManager.startLoading(uuid, tag)
        coroutineContext[Job]?.invokeOnCompletion {
            loadingManager.stopLoading(uuid, tag)
        }
    }

    fun <T> Flow<T>.withLoading() {
        val uuid = UUID.randomUUID().toString()
        onStart { loadingManager.startLoading(uuid, tag) }
        onCompletion { loadingManager.stopLoading(uuid, tag) }
    }

    override fun onCleared() {
        loadingManager.stopScope(tag)
        super.onCleared()
    }
}