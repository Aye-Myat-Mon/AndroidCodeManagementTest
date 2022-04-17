package com.android.amm.androidcodemanagementtest.di

import androidx.fragment.app.FragmentFactory
import com.android.amm.androidcodemanagementtest.di.factory.MainFragmentFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FragmentFactoryModule {
    @Binds
    internal abstract fun mainFragmentFactoryModule(mainFragmentFactory: MainFragmentFactory): FragmentFactory
}