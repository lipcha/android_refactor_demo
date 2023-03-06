package com.barges.di

import com.barges.concurency.DefaultErrorHandler
import com.barges.concurency.DefaultErrorParser
import com.barges.concurency.ErrorHandler
import com.barges.concurency.ErrorParser
import com.barges.dialog_manager.DialogManager
import com.barges.dialog_manager.DialogManagerImpl
import com.barges.loading_manager.LoadingManager
import com.barges.loading_manager.LoadingManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindLoadingManager(loadingManagerImpl: LoadingManagerImpl): LoadingManager

    @Binds
    @ActivityRetainedScoped
    abstract fun bindErrorHandler(errorHandler: DefaultErrorHandler): ErrorHandler

    @Binds
    @ActivityRetainedScoped
    abstract fun bindErrorParser(errorParser: DefaultErrorParser): ErrorParser

    @Binds
    @ActivityRetainedScoped
    abstract fun bindDialogManager(dialogManager: DialogManagerImpl): DialogManager
}