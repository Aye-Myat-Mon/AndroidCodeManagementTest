package com.android.amm.androidcodemanagementtest.view.ui.movielist

import androidx.lifecycle.ViewModel
import com.android.amm.androidcodemanagementtest.di.FragmentScoped
import com.android.amm.androidcodemanagementtest.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class MovieListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMovieListFragment(): MovieListFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMovieListViewModel(vm: MovieListViewModel): ViewModel
}