package com.training.victor.development.ui

import android.widget.ImageView
import com.training.victor.development.data.models.MovieItem

interface MovieClickListener {
    fun onMovieClick(imgMovie: ImageView, movie: MovieItem)
}