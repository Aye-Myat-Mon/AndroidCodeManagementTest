package com.android.amm.androidcodemanagementtest.view.ui.movielist

import androidx.lifecycle.ViewModel
import com.android.amm.androidcodemanagementtest.di.FragmentScoped
import com.android.amm.androidcodemanagementtest.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by ayemyatmon on 17,April,2022
 */
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