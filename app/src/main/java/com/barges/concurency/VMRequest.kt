package com.barges.concurency

import com.barges.loading_manager.Loader
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class VMRequest {

    var loader: Loader? = null

    var errorInterceptor: ((Throwable) -> Boolean)? = null

    var errorParser: ErrorParser? = null

    var errorHandler: ErrorHandler? = null

    protected val coroutineErrorHandler = CoroutineExceptionHandler { _, error ->
        val parsedError = errorParser?.parse(error) ?: return@CoroutineExceptionHandler
        if (errorInterceptor?.invoke(parsedError) == false)
            errorHandler?.handleError(parsedError)
    }

    abstract fun execute()
}