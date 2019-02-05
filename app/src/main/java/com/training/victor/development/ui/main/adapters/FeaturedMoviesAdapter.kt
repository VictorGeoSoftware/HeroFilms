package com.training.victor.development.ui.main.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.ui.MovieClickListener
import com.training.victor.development.ui.main.FeaturedMovieFragment

class FeaturedMoviesAdapter(fragmentManager: FragmentManager,
                            private val moviesList: ArrayList<MovieItem>,
                            private val movieClickListener: MovieClickListener): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): FeaturedMovieFragment {
        return FeaturedMovieFragment.newInstance(
            moviesList[position],
            movieClickListener
        )
    }

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE

    override fun getCount(): Int = moviesList.size
}