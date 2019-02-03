package com.training.victor.development.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.training.victor.development.MainApplication
import com.training.victor.development.R
import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.presenter.ThorFilmsPresenter
import com.training.victor.development.ui.utils.SpaceDecorator
import com.training.victor.development.utils.getDpFromValue
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ThorFilmsPresenter.ThorFilmsView {

    companion object {
        private const val MOVIES_LIST = "MOVIES_LIST"
        private const val FEATURED_MOVIES_LIST = "FEATURED_MOVIES_LIST"
    }

    @Inject lateinit var thorFilmsPresenter: ThorFilmsPresenter

    private var mMoviesList = ArrayList<MovieItem>()
    private lateinit var mMoviesAdapter: MoviesAdapter

    private var mFeaturedMoviesList = ArrayList<MovieItem>()
    private lateinit var mFeaturedMoviesAdapter: FeaturedMoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).createPresenterComponent().inject(this)


        thorFilmsPresenter.view = this

        if (savedInstanceState != null) {
            mMoviesList = savedInstanceState.getParcelableArrayList(MOVIES_LIST) ?: ArrayList()
            mFeaturedMoviesList = savedInstanceState.getParcelableArrayList(FEATURED_MOVIES_LIST) ?: ArrayList()
        } else {
            thorFilmsPresenter.getMoviesList()
            thorFilmsPresenter.getFeaturedMoviesList()
        }

        val myGridLayoutManager = LinearLayoutManager(this)
        lstMovies.layoutManager = myGridLayoutManager
        lstMovies.addItemDecoration(SpaceDecorator(getDpFromValue(10)))
        mMoviesAdapter = MoviesAdapter(mMoviesList)
        lstMovies.adapter = mMoviesAdapter

        mFeaturedMoviesAdapter = FeaturedMoviesAdapter(supportFragmentManager, mFeaturedMoviesList)
        autoScrollViewPager.adapter = mFeaturedMoviesAdapter
        tabLayoutDotsIndicator.setupWithViewPager(autoScrollViewPager)
    }

    override fun onResume() {
        super.onResume()
        autoScrollViewPager.startAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        autoScrollViewPager.stopAutScroll()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList(MOVIES_LIST, mMoviesList)
        outState?.putParcelableArrayList(FEATURED_MOVIES_LIST, mFeaturedMoviesList)
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as MainApplication).releasePresenterComponent()
    }



    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- PRESENTER VIEW ---------------------------------------------
    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onMoviesListReceived(moviesList: List<MovieItem>) {
        mMoviesList.clear()
        mMoviesList.addAll(moviesList)
        mMoviesAdapter.notifyDataSetChanged()
    }

    override fun onFeaturedMoviesError() {
        mFeaturedMoviesList.clear()
        mFeaturedMoviesAdapter.notifyDataSetChanged()
        TODO("Some animation or UI component for showing error cause to user")
    }

    override fun onFeaturedMoviesReceived(featuredMoviesList: List<MovieItem>) {
        mFeaturedMoviesList.addAll(featuredMoviesList)
        mFeaturedMoviesAdapter.notifyDataSetChanged()
    }

    override fun onMoviesListError() {
        mMoviesList.clear()
        mMoviesAdapter.notifyDataSetChanged()
        TODO("Some animation or UI component for showing error cause to user")
    }
}
