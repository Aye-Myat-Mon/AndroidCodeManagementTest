package com.android.amm.androidcodemanagementtest.models


data class MovieListUiState(
    val movieList: List<MovieModel>? = null,
    val isMovieListEmpty: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
