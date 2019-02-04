package com.training.victor.development.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.training.victor.development.BuildConfig
import com.training.victor.development.MainApplication
import com.training.victor.development.R
import com.training.victor.development.data.Constants.Companion.IMAGE_BIG
import com.training.victor.development.data.models.MovieDetailItem
import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.presenter.ThorFilmsPresenter
import com.training.victor.development.utils.showRequestErrorMessage
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject
import android.net.Uri


class MovieDetailActivity: AppCompatActivity(), ThorFilmsPresenter.ThorFilmsView {

    @Inject lateinit var mThorFilmsPresenter: ThorFilmsPresenter
    private var mSelectedMovie: MovieDetailItem? = null

    companion object {
        private const val EXTRA_SELECTED_MOVIE_ID = "EXTRA_SELECTED_MOVIE_ID"
        private const val DETAILED_MOVIE = "DETAILED_MOVIE"

        fun loadMovieDetailActivity(context: Context , selectedMovieId: Int) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_SELECTED_MOVIE_ID, selectedMovieId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        (application as MainApplication).createPresenterComponent().inject(this)

        mThorFilmsPresenter.view = this

        if (savedInstanceState != null) {
            mSelectedMovie = savedInstanceState.getParcelable(DETAILED_MOVIE)
        } else {
            val selectedMovieId = intent.getIntExtra(EXTRA_SELECTED_MOVIE_ID, 0)

            if (selectedMovieId != 0) {
                mThorFilmsPresenter.getMovieDetails(selectedMovieId)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mSelectedMovie?.let { outState?.putParcelable(DETAILED_MOVIE, mSelectedMovie) }
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as MainApplication).releasePresenterComponent()
    }



    override fun onMovieDetailsReceived(movieDetails: MovieDetailItem) {
        mSelectedMovie = movieDetails
        populateView(movieDetails)
    }

    override fun onMovieDetailsError(throwable: Throwable) {
        showRequestErrorMessage(getString(R.string.movie_details_error))
    }

    override fun showProgressBar(show: Boolean) { }

    override fun onMoviesListReceived(moviesList: List<MovieItem>) { }

    override fun onMoviesListError() { }

    override fun onFeaturedMoviesReceived(featuredMoviesList: List<MovieItem>) { }

    override fun onFeaturedMoviesError() { }


    private fun populateView(movieDetails: MovieDetailItem) {
        txtTitle.text = movieDetails.title
        txtDetailDate.text = movieDetails.releaseDate

        val runtime = String.format(getString(R.string.movie_runtime), movieDetails.runtime.toString())
        txtRuntime.text = runtime

        val rate = String.format(getString(R.string.movie_rate), movieDetails.voteAverage.toString())
        txtRate.text = rate

        movieDetails.homepage?.let {url ->
            txtUrl.text = url
            txtUrl.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
            }
        }

        txtOverviewExtended.text = movieDetails.overview

        val imageUrl = BuildConfig.IMAGES_URL + IMAGE_BIG + movieDetails.posterPath
        Glide.with(this).load(imageUrl).into(imgDetail)
    }
}