package com.barges.concurency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CoroutineRequest(
    private val scope: CoroutineScope,
    private val context: CoroutineContext,
    private val block: suspend () -> Unit
) : VMRequest() {

    override fun execute() {
        scope.launch(context + coroutineErrorHandler) {
            loader?.start()
            coroutineContext[Job]?.invokeOnCompletion {
                loader?.stop()
            }
            block()
        }
    }
}
