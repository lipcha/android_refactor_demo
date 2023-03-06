package com.barges.concurency

import com.barges.loading_manager.LoadingManager
import kotlinx.coroutines.CoroutineScope

class DefaultExecutable constructor(
    override val loadingManager: LoadingManager,
    override val errorParser: ErrorParser?,
    override val errorHandler: ErrorHandler?,
    override val coroutineScope: CoroutineScope? = null
) : Executable