package com.training.victor.development.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.training.victor.development.BuildConfig
import com.training.victor.development.R
import com.training.victor.development.data.Constants.Companion.IMAGE_BIG
import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.ui.MovieClickListener
import kotlinx.android.synthetic.main.fragment_featured_movie.*

class FeaturedMovieFragment: Fragment() {

    companion object {
        private const val ARG_MOVIE = "movie"

        fun newInstance(movie: MovieItem, movieClickListener: MovieClickListener): FeaturedMovieFragment {
            val args = Bundle()
            args.putParcelable(ARG_MOVIE, movie)

            val fragment = FeaturedMovieFragment()
            fragment.arguments = args
            fragment.movieClickListener = movieClickListener
            return fragment
        }
    }

    private var movieClickListener: MovieClickListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_featured_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val movie: MovieItem? = arguments?.getParcelable(ARG_MOVIE)

        val imageUrl = BuildConfig.IMAGES_URL + IMAGE_BIG + movie?.posterPath
        context?.let { Glide.with(it).load(imageUrl).into(featuredMovieImage) }

        featuredMovieImage.setOnClickListener {
            movie?.let {
                movieClickListener?.onMovieClick(featuredMovieImage, null, movie)
            }
        }
    }
}