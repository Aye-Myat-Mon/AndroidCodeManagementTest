package com.android.amm.androidcodemanagementtest.di

import android.content.Context
import com.android.amm.androidcodemanagementtest.MainApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }
}