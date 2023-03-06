package com.barges.concurency

import com.barges.loading_manager.DefaultLoader
import com.barges.loading_manager.Loader
import com.barges.loading_manager.LoadingManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import java.util.*
import kotlin.coroutines.CoroutineContext

interface Executable {

    val loadingScope: String
        get() = UUID.randomUUID().toString()

    var coroutineScope: CoroutineScope?

    val loadingManager: LoadingManager

    val errorParser: ErrorParser?

    val errorHandler: ErrorHandler?

    fun Flow<*>.request(context: CoroutineContext = Dispatchers.Default): VMRequest {
        coroutineScope ?: throw RuntimeException("'coroutineScope' is not initialized. Please provide the 'coroutineScope' first")
        return FlowRequest(coroutineScope!!, context, this)
            .errorParser(errorParser)
            .errorHandler(errorHandler)
    }

    fun Executable.request(context: CoroutineContext = Dispatchers.Default, block: suspend () -> Unit): VMRequest {
        coroutineScope ?: throw RuntimeException("'coroutineScope' is not initialized. Please provide the 'coroutineScope' first")
        return CoroutineRequest(coroutineScope!!, context, block)
            .errorParser(errorParser)
            .errorHandler(errorHandler)
    }

    fun VMRequest.withLoader(loader: Loader = DefaultLoader(loadingManager, loadingScope)) = apply { this.loader = loader }

    fun VMRequest.interceptError(interceptor: ((Throwable) -> Boolean)) = apply { this.errorInterceptor = interceptor }

    fun VMRequest.errorParser(errorParser: ErrorParser?) = apply { this.errorParser = errorParser }

    fun VMRequest.errorHandler(errorHandler: ErrorHandler?) = apply { this.errorHandler = errorHandler }
}