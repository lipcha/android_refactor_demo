package com.barges.loading_manager

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class LoadingManagerImpl(coroutineScope: CoroutineScope): LoadingManager {

    private val _loaders = MutableStateFlow(mutableSetOf<String>())

    override val isLoading = _loaders.map { it.isNotEmpty() }.stateIn(coroutineScope, SharingStarted.Lazily, false)

    override fun startLoading(uuid: String, scopeTag: String) {
        _loaders.value = _loaders.value.apply { add("$scopeTag/$uuid") }
    }

    override fun stopLoading(uuid: String, scopeTag: String) {
        _loaders.value = _loaders.value.apply { remove("$scopeTag/$uuid") }
    }

    override fun stopScope(scopeTag: String) {
        _loaders.value = _loaders.value.filter { !it.contains(scopeTag) }.toMutableSet()
    }
}