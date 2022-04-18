package com.android.amm.androidcodemanagementtest.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieModel(
    @PrimaryKey val id: String,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val vote_count: Int,
    val release_date: String,
    val vote_average: String,
    var isFavourite: Boolean = false,
    var isPopular: Boolean = false
)
