package com.android.amm.androidcodemanagementtest.view.ui.moviedetail

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
abstract class MovieDetailModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetailFragment(): MovieDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieDetailViewModel(vm: MovieDetailViewModel): ViewModel
}