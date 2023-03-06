package com.barges.concurency

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

interface ErrorParser {

    fun parse(throwable: Throwable): Throwable

}

@ActivityRetainedScoped
class DefaultErrorParser @Inject constructor() : ErrorParser {

    override fun parse(throwable: Throwable): Throwable {
        return throwable
    }

}