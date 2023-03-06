package com.barges.vm

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barges.concurency.Executable

abstract class BaseVM constructor(
    open val executable: Executable
) : ViewModel(), DefaultLifecycleObserver, Executable by executable {

    init {
        // todo is not the best solution. I'm not proud of it, but at least it works :))
        coroutineScope = viewModelScope
    }

    override fun onCleared() {
        loadingManager.stopScope(executable.loadingScope)
        super.onCleared()
    }
}