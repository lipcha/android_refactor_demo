package com.barges.loading_manager

import android.util.Log
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityRetainedScoped
class LoadingManagerImpl @Inject constructor() : LoadingManager {

    init {
        Log.d("MyTag", "init LoadingManagerImpl")
    }

    private val _loaders = MutableStateFlow(listOf<String>())

    override val isLoading: Flow<Boolean> = _loaders.map { it.isNotEmpty() }

    override fun startLoading(uuid: String, scope: String) {
        _loaders.value = _loaders.value.toMutableList().apply { add("$scope/$uuid") }
    }

    override fun stopLoading(uuid: String, scope: String) {
        _loaders.value = _loaders.value.toMutableList().apply { remove("$scope/$uuid") }
    }

    override fun stopScope(scope: String) {
        _loaders.value = _loaders.value.filter { !it.contains(scope) }
    }
}