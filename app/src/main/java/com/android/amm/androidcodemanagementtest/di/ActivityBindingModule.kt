package com.android.amm.androidcodemanagementtest.di

import com.android.amm.androidcodemanagementtest.view.ui.MainActivity
import com.android.amm.androidcodemanagementtest.view.ui.moviedetail.MovieDetailModule
import com.android.amm.androidcodemanagementtest.view.ui.movielist.MovieListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [MovieListModule::class, MovieDetailModule::class])
    abstract fun contributeMainActivity(): MainActivity
}