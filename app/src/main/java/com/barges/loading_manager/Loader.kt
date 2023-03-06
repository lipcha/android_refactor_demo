package com.barges.loading_manager

import android.util.Log
import java.util.*

interface Loader {

    fun start()

    fun stop()
}

class DefaultLoader(private val loadingManager: LoadingManager?, private val scope: String) : Loader {

    private val uuid: String = UUID.randomUUID().toString()

    override fun start() {
        Log.d("MyTag", "Loader.start")
        loadingManager?.startLoading(uuid, scope)
    }

    override fun stop() {
        Log.d("MyTag", "Loader.stop")
        loadingManager?.stopLoading(uuid, scope)
    }
}

class LoaderMediator(private val loaders: List<Loader>): Loader{
    override fun start() {
        loaders.forEach { _ -> this.start() }
    }

    override fun stop() {
        loaders.forEach { _ -> this.stop() }
    }
}