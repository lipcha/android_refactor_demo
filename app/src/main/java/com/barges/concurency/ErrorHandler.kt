package com.barges.concurency

import com.barges.dialog_manager.DialogConfig
import com.barges.dialog_manager.DialogManager
import com.barges.dialog_manager.DialogStrategy
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

interface ErrorHandler {

    fun handleError(throwable: Throwable)
}

@ActivityRetainedScoped
class DefaultErrorHandler @Inject constructor(private val dialogManager: DialogManager) : ErrorHandler {

    override fun handleError(throwable: Throwable) {
        dialogManager.showDialog(DialogConfig(DialogStrategy.ALWAYS_TOP))
    }

}