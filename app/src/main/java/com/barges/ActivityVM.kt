package com.barges

import androidx.lifecycle.viewModelScope
import com.barges.concurency.Executable
import com.barges.vm.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ActivityVM @Inject constructor(executable: Executable) : BaseVM(executable) {

    fun isLoading(): StateFlow<Boolean> = loadingManager.isLoading.stateIn(viewModelScope, SharingStarted.Lazily, false)
}