package com.android.amm.androidcodemanagementtest.view.ui.movielist

import androidx.lifecycle.*
import com.android.amm.androidcodemanagementtest.data.repository.MovieRepository
import com.android.amm.androidcodemanagementtest.models.MovieListUiState
import com.android.amm.androidcodemanagementtest.models.MovieModel
import com.android.amm.androidcodemanagementtest.utils.Result
import com.android.amm.androidcodemanagementtest.utils.successOr
import kotlinx.coroutines.launch
import javax.inject.Inject


class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val popularMovieResultMediator = MediatorLiveData<MovieListUiState>()

    val popularMovieListUiState: LiveData<MovieListUiState?> = Transformations.map(popularMovieResultMediator) {
        it
    }

    private val upcomingMovieResultMediator = MediatorLiveData<MovieListUiState>()

    val upcomingMovieListUiState: LiveData<MovieListUiState?> = Transformations.map(upcomingMovieResultMediator) {
        it
    }

    init {
        getPopularMovies()
        getUpcomingMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            movieRepository.getPopularMovies().collect {
                when (it) {
                    is Result.Success<List<MovieModel>> -> {
                        it.successOr(null)?.let { response ->
                            popularMovieResultMediator.value = MovieListUiState(
                                movieList = response,
                                isMovieListEmpty = response.isEmpty()
                            )
                        }
                    }
                    is Result.Error -> {
                        popularMovieResultMediator.value = MovieListUiState(movieList = null,
                            isMovieListEmpty = true, isLoading = false, errorMessage = it.exception.message
                        )
                    }

                    is Result.Loading -> {
                        popularMovieResultMediator.value = MovieListUiState(movieList = null,
                            isMovieListEmpty = true, isLoading = true, errorMessage = null
                        )
                    }
                    else -> {}
                }
            }
        }

    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            movieRepository.getUpcomingMovies().collect {
                when (it) {
                    is Result.Success<List<MovieModel>> -> {
                        it.successOr(null)?.let { response ->
                            upcomingMovieResultMediator.value = MovieListUiState(
                                movieList = response,
                                isMovieListEmpty = response.isEmpty()
                            )
                        }
                    }
                    is Result.Error -> {
                        upcomingMovieResultMediator.value = MovieListUiState(movieList = null,
                            isMovieListEmpty = true, isLoading = false, errorMessage = it.exception.message
                        )
                    }

                    is Result.Loading -> {
                        upcomingMovieResultMediator.value = MovieListUiState(movieList = null,
                            isMovieListEmpty = true, isLoading = true, errorMessage = null
                        )
                    }
                    else -> {}
                }
            }
        }

    }

    fun updateMovie(movieModel: MovieModel) {
        viewModelScope.launch {
            movieRepository.updateMovieData(movieModel)
        }
    }
}