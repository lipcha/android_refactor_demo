package com.barges.dialog_manager

import kotlinx.coroutines.flow.StateFlow

interface DialogManager {

    val currentDialogFlow: StateFlow<DialogConfig?>

    fun showDialog(dialogConfig: DialogConfig)

    fun onDismissed(dialogId: String)
}