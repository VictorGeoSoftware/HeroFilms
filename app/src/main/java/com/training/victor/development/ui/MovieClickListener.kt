package com.training.victor.development.ui

import android.widget.ImageView
import android.widget.TextView
import com.training.victor.development.data.models.MovieItem

interface MovieClickListener {
    fun onMovieClick(imageView: ImageView, txtTitle: TextView?, movie: MovieItem)
}