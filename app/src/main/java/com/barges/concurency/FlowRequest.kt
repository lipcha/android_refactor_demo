package com.barges.concurency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FlowRequest(
    private val scope: CoroutineScope, private val context: CoroutineContext, private val flow: Flow<*>
) : VMRequest() {

    override fun execute() {
        scope.launch(context + coroutineErrorHandler) {
            flow.onStart { loader?.start() }.onCompletion { loader?.stop() }.collect()
        }
    }

}