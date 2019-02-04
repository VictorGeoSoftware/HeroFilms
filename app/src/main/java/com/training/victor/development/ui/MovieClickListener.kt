package com.training.victor.development.ui

import com.training.victor.development.data.models.MovieItem

interface MovieClickListener {
    fun onMovieClick(movie: MovieItem)
}