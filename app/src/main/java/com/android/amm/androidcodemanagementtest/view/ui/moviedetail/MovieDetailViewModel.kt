package com.android.amm.androidcodemanagementtest.view.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.amm.androidcodemanagementtest.data.repository.MovieRepository
import com.android.amm.androidcodemanagementtest.models.MovieModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ayemyatmon on 17,April,2022
 */
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    fun updateMovie(movieModel: MovieModel) {
        viewModelScope.launch {
            movieRepository.updateMovieData(movieModel)
        }
    }
}