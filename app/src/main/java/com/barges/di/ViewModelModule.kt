package com.barges.di

import com.barges.concurency.*
import com.barges.loading_manager.LoadingManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MainActivityModule {

    @Provides
    @ViewModelScoped
    fun providerExecutable(
        loadingManager: LoadingManager,
        errorParser: ErrorParser,
        errorHandler: ErrorHandler
    ): Executable {
        return DefaultExecutable(loadingManager, errorParser, errorHandler)
    }
}
