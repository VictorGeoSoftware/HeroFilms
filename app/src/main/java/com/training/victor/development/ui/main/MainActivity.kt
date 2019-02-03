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

    @Inject lateinit var thorFilmsPresenter: ThorFilmsPresenter

    private val mMoviesList = ArrayList<MovieItem>()
    private lateinit var mMoviesAdapter: MoviesAdapter

    private val mFeaturedMoviesList = ArrayList<MovieItem>()
    private lateinit var mFeaturedMoviesAdapter: FeaturedMoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).createPresenterComponent().inject(this)

        val myGridLayoutManager = LinearLayoutManager(this)
        lstMovies.layoutManager = myGridLayoutManager
        lstMovies.addItemDecoration(SpaceDecorator(getDpFromValue(10)))
        mMoviesAdapter = MoviesAdapter(mMoviesList)
        lstMovies.adapter = mMoviesAdapter

        mFeaturedMoviesAdapter = FeaturedMoviesAdapter(supportFragmentManager, mFeaturedMoviesList)
        autoScrollViewPager.adapter = mFeaturedMoviesAdapter
        tabLayoutDotsIndicator.setupWithViewPager(autoScrollViewPager)

        thorFilmsPresenter.view = this
        thorFilmsPresenter.getMoviesList()
    }

    override fun onResume() {
        super.onResume()
        autoScrollViewPager.startAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        autoScrollViewPager.stopAutScroll()
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

    override fun onMoviesListReceived(profilesList: List<MovieItem>) {
        mMoviesList.clear()
        mMoviesList.addAll(profilesList)
        mMoviesAdapter.notifyDataSetChanged()

        mFeaturedMoviesList.addAll(profilesList)
        mFeaturedMoviesAdapter.notifyDataSetChanged()
    }

    override fun onMoviesListError() {
        mMoviesList.clear()
        mMoviesAdapter.notifyDataSetChanged()
    }
}
