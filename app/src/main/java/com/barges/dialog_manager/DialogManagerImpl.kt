package com.barges.dialog_manager

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import javax.inject.Inject

@ActivityRetainedScoped
class DialogManagerImpl @Inject constructor() : DialogManager, Comparator<DialogConfig> {

    private val _currentDialogFlow = MutableStateFlow<DialogConfig?>(null)

    private val dialogsQueue = PriorityQueue(11, this)

    override val currentDialogFlow: StateFlow<DialogConfig?>
        get() = _currentDialogFlow

    override fun showDialog(dialogConfig: DialogConfig) {
        dialogsQueue.offer(dialogConfig)
        if (dialogsQueue.size == 1)
            showNext()
    }

    override fun onDismissed(dialogId: String) {
        showNext()
    }

    override fun compare(o1: DialogConfig, o2: DialogConfig): Int {
        return o1.strategy.compareTo(o2.strategy)
    }

    private fun showNext() {
        _currentDialogFlow.value = dialogsQueue.poll()
    }
}