package com.android.amm.androidcodemanagementtest.view.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.amm.androidcodemanagementtest.R
import com.android.amm.androidcodemanagementtest.constant.Constants
import com.android.amm.androidcodemanagementtest.models.MovieModel
import com.android.amm.androidcodemanagementtest.view.ui.MainActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject


class MovieDetailFragment: DaggerFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel: MovieDetailViewModel by lazy {
        ViewModelProvider(this, vmFactory)[MovieDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieData = Gson().fromJson(arguments?.getString(key_movie_data), MovieModel::class.java)

        if (movieData != null) {
            bindUi(movieData)
        }

        tb_favourite.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateMovie(movieData.apply { isFavourite = isChecked })
        }

        ib_back.setOnClickListener {
            (requireActivity() as MainActivity).openMovieListFragment()
        }
    }

    private fun bindUi(movieData: MovieModel) {
        tv_title.text = movieData.title
        tv_overview.text = movieData.overview
        tv_percent.text = movieData.vote_average
        tv_date.text = movieData.release_date
        tv_vote.text = "${movieData.vote_count}votes"
        tb_favourite.isChecked = movieData.isFavourite

        Glide.with(requireActivity())
            .load(Constants.POSTER_BASE_URL + movieData.poster_path)
            .into(iv_movie_path)
    }

    companion object {
        const val tag_movie_detail = "tag_movie_detail"
        const val key_movie_data = "key_movie_data"
    }
}