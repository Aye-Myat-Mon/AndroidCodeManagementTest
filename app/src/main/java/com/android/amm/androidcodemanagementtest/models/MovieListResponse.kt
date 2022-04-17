package com.android.amm.androidcodemanagementtest.models

data class MovieListResponse(
    val results: List<MovieResponseModel>
)

data class MovieResponseModel(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val vote_count: Int,
    val release_date: String,
    val vote_average: String,
    var isFavourite: Boolean = false
)