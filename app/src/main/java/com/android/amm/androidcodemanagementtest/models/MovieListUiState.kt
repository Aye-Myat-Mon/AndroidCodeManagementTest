package com.android.amm.androidcodemanagementtest.models

/**
 * Created by ayemyatmon on 17,April,2022
 */
data class MovieListUiState(
    val movieList: List<MovieModel>? = null,
    val isMovieListEmpty: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
