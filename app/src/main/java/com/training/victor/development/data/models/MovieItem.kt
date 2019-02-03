package com.training.victor.development.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem (val id: Int = 0,
                      val title: String = "",
                      val voteAverage: Double = 0.0,
                      val releaseDate: String = "",
                      val overview: String = "",
                      val posterPath: String = "") : Parcelable