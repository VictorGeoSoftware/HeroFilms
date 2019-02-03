package com.training.victor.development.data

import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.network.responses.models.FilmItem


fun FilmItem.toMovieItem(): MovieItem {
    return MovieItem(
        this.id,
        this.originalTitle,
        this.voteAverage,
        this.releaseDate,
        this.overview,
        this.posterPath
    )
}