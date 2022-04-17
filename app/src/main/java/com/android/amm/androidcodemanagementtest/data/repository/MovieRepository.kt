package com.android.amm.androidcodemanagementtest.data.repository

import com.android.amm.androidcodemanagementtest.constant.Constants
import com.android.amm.androidcodemanagementtest.data.api.MovieApi
import com.android.amm.androidcodemanagementtest.data.db.MovieDao
import com.android.amm.androidcodemanagementtest.models.MovieListResponse
import com.android.amm.androidcodemanagementtest.models.MovieModel
import com.android.amm.androidcodemanagementtest.utils.Result
import com.android.amm.androidcodemanagementtest.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ayemyatmon on 17,April,2022
 */
class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) {

    suspend fun getPopularMovies(): Flow<Result<List<MovieModel>>> {
        return flow {
            emit(fetchPopularMoviesCached())
            emit(Result.Loading)

            val movieList = ArrayList<MovieModel>()

            when (val response = safeApiCall { movieApi.getPopularMovies(Constants.API_KEY) }) {
                is Result.Success -> {
                    for (movie in response.data.results) {
                        movieList.add(
                            MovieModel(
                                movie.id.toString(),
                                movie.title,
                                movie.overview,
                                movie.poster_path,
                                movie.vote_count,
                                movie.release_date,
                                movie.vote_average,
                                movie.isFavourite,
                                true
                            )
                        )
                    }
                    movieDao.insertMovies(movieList)
                    emit(Result.Success(movieDao.getMovies(isPopular = true)))
                }
                is Result.Error -> {
                    emit(Result.Error(response.exception))
                }
                else -> {
                    emit(Result.Empty)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUpcomingMovies(): Flow<Result<List<MovieModel>>> {
        return flow {
            emit(fetchUpcomingMoviesCached())
            emit(Result.Loading)

            val movieList = ArrayList<MovieModel>()

            when (val response = safeApiCall { movieApi.getUpcomingMovies(Constants.API_KEY) }) {
                is Result.Success -> {
                    for (movie in response.data.results) {
                        movieList.add(
                            MovieModel(
                                movie.id.toString(),
                                movie.title,
                                movie.overview,
                                movie.poster_path,
                                movie.vote_count,
                                movie.release_date,
                                movie.vote_average,
                                movie.isFavourite,
                                false
                            )
                        )
                    }
                    movieDao.insertMovies(movieList)
                    (Timber.d("amm: movie list ${movieDao.getMovies(isPopular = false)}"))
                    emit(Result.Success(movieDao.getMovies(isPopular = false)))
                }
                is Result.Error -> {
                    emit(Result.Error(response.exception))
                }
                else -> {
                    emit(Result.Empty)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateMovieData(movieModel: MovieModel) {
        return withContext(Dispatchers.IO) {
            Timber.d("amm: is favourite ${movieModel.title} ${movieModel.id}")
            movieDao.updateMovie(movieModel.isFavourite, movieModel.id)
        }
    }

    private fun fetchPopularMoviesCached(): Result<List<MovieModel>> =
        movieDao.getMovies(isPopular = true).let {
            Result.Success(it)
        }

    private fun fetchUpcomingMoviesCached(): Result<List<MovieModel>> =
        movieDao.getMovies(isPopular = false).let {
            Result.Success(it)
        }
}