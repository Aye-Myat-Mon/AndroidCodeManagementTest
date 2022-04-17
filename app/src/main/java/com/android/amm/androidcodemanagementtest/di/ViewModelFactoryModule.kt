package com.android.amm.androidcodemanagementtest.di

import androidx.lifecycle.ViewModelProvider
import com.android.amm.androidcodemanagementtest.di.factory.MainViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun provideViewModelFactoryModule(mainViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}