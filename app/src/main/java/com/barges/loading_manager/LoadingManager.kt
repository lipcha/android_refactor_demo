package com.barges.loading_manager

import kotlinx.coroutines.flow.Flow

interface LoadingManager {

    val isLoading: Flow<Boolean>

    fun startLoading(uuid: String, scope: String)

    fun stopLoading(uuid: String, scope: String)

    fun stopScope(scope: String)
}