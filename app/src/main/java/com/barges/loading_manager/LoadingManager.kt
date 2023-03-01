package com.barges.loading_manager

import kotlinx.coroutines.flow.StateFlow

interface LoadingManager {

    val isLoading: StateFlow<Boolean>

    fun startLoading(uuid: String, scopeTag: String)

    fun stopLoading(uuid: String, scopeTag: String)

    fun stopScope(scopeTag: String)
}