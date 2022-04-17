package com.android.amm.androidcodemanagementtest.view.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.amm.androidcodemanagementtest.R
import com.android.amm.androidcodemanagementtest.models.ErrorMessageModel
import com.android.amm.androidcodemanagementtest.models.MovieListUiState
import com.android.amm.androidcodemanagementtest.models.MovieModel
import com.android.amm.androidcodemanagementtest.view.ui.MainActivity
import com.google.gson.Gson
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_list.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ayemyatmon on 17,April,2022
 */
class MovieListFragment: DaggerFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this, vmFactory)[MovieListViewModel::class.java]
    }

    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var upcomingMovieAdapter: UpcomingMovieAdapter

    private var popularMovies: List<MovieModel> = ArrayList()
    private var upcomingMovies: List<MovieModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.popularMovieListUiState.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            bindPopularMovieUi(it)
        }

        viewModel.upcomingMovieListUiState.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            bindUpcomingMovieUi(it)
        }
    }

    private fun bindPopularMovieUi(movieListUiState: MovieListUiState) {
        if (!movieListUiState.isMovieListEmpty) {
            popularMovies = movieListUiState.movieList!!
            initPopularMovieListRecycler()

            tv_error_text.visibility = View.GONE
            hideLoading()
        } else {
            tv_error_text.visibility = View.VISIBLE
            hideLoading()
        }

        if (movieListUiState.errorMessage != null) {
            showErrorMessage(movieListUiState.errorMessage)
        }

        if (movieListUiState.isLoading) {
            showLoading()
        }
    }

    private fun bindUpcomingMovieUi(movieListUiState: MovieListUiState) {
        if (!movieListUiState.isMovieListEmpty) {
            upcomingMovies = movieListUiState.movieList!!
            initUpcomingMovieListRecycler()
            tv_error_text.visibility = View.GONE
            hideLoading()
        } else {
            tv_error_text.visibility = View.VISIBLE
            hideLoading()
        }

        if (movieListUiState.errorMessage != null) {
            showErrorMessage(movieListUiState.errorMessage)
        }

        if (movieListUiState.isLoading) {
            showLoading()
        }
    }

    private fun showErrorMessage(errorMessage: String?) {
        val errMessage = Gson().fromJson(errorMessage, ErrorMessageModel::class.java)
        Toast.makeText(requireContext(), errMessage.message, Toast.LENGTH_LONG).show()
    }

    private fun initPopularMovieListRecycler() {

        popularMovieAdapter = PopularMovieAdapter(popularMovies)

        popularMovieAdapter.addOnMovieClickListener {
            (requireActivity() as MainActivity).openMovieDetailFragment(it)
        }

        popularMovieAdapter.addOnFavouriteClickListener {
            viewModel.updateMovie(it)
        }

        rv_popular_movie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_popular_movie.adapter = popularMovieAdapter

    }

    private fun initUpcomingMovieListRecycler() {

        upcomingMovieAdapter = UpcomingMovieAdapter(upcomingMovies)

        upcomingMovieAdapter.addOnMovieClickListener {
            (requireActivity() as MainActivity).openMovieDetailFragment(it)
        }

        upcomingMovieAdapter.addOnFavouriteClickListener {
            viewModel.updateMovie(it)
        }

        rv_upcoming_movie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_upcoming_movie.adapter = upcomingMovieAdapter

    }

    private fun showLoading() {
        tv_error_text.visibility = View.GONE
        pb_loading.visibility = View.VISIBLE
        tv_popular.visibility = View.GONE
        rv_popular_movie.visibility = View.GONE
        tv_upcoming.visibility = View.GONE
        rv_upcoming_movie.visibility = View.GONE
    }

    private fun hideLoading() {
        pb_loading.visibility = View.GONE
        tv_error_text.visibility = View.GONE
        tv_popular.visibility = View.VISIBLE
        rv_popular_movie.visibility = View.VISIBLE
        tv_upcoming.visibility = View.VISIBLE
        rv_upcoming_movie.visibility = View.VISIBLE
    }

    companion object {
        const val tag_movie_list = "tag_movie_list"
    }
}