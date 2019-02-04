package com.training.victor.development.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailItem (val title: String = "",
                            val releaseDate: String = "",
                            val runtime: Int = 0,
                            val voteAverage: Double = 0.0,
                            val overview: String = "",
                            var homepage: String? = null,
                            val posterPath: String = "") : Parcelable