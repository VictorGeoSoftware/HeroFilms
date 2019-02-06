package com.training.victor.development.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.transition.TransitionManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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


class MovieDetailActivity: AppCompatActivity(), ThorFilmsPresenter.ThorFilmsView {

    @Inject lateinit var mThorFilmsPresenter: ThorFilmsPresenter
    private var mSelectedMovie: MovieDetailItem? = null

    companion object {
        private const val EXTRA_SELECTED_MOVIE_ID = "EXTRA_SELECTED_MOVIE_ID"
        private const val DETAILED_MOVIE = "DETAILED_MOVIE"

        fun loadMovieDetailActivity(
            context: Context,
            selectedMovieId: Int,
            options: ActivityOptionsCompat
        ) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_SELECTED_MOVIE_ID, selectedMovieId)
            context.startActivity(intent, options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportPostponeEnterTransition()
        (application as MainApplication).createPresenterComponent().inject(this)

        imgDetail.transitionName = getString(R.string.main_transition)

        val fadeTransition = TransitionInflater.from(this).inflateTransition(R.transition.transition_fade)
        fadeTransition.duration = 500
        TransitionManager.beginDelayedTransition(frameLayoutTransition, fadeTransition)

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

    override fun onBackPressed() {
        finishAfterTransition()
    }

    override fun onDestroy() {
        supportFinishAfterTransition()
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
        Glide.with(this).load(imageUrl).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                                      isFirstResource: Boolean): Boolean {
                supportStartPostponedEnterTransition()
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                         dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                supportStartPostponedEnterTransition()
                return false
            }
        }).into(imgDetail)
    }
}