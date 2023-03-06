package com.barges.vm

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barges.concurency.Executable
import kotlinx.coroutines.CoroutineScope

abstract class BaseVM constructor(
    open val executable: Executable
) : ViewModel(), DefaultLifecycleObserver, Executable by executable {

    override val coroutineScope: CoroutineScope?
        get() = viewModelScope

    override fun onCleared() {
        loadingManager.stopScope(executable.loadingScope)
        super.onCleared()
    }
}