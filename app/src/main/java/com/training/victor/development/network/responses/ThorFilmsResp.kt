package com.training.victor.development.network.responses

import com.google.gson.annotations.SerializedName
import com.training.victor.development.network.responses.models.FilmItem

data class ThorFilmsResp(@SerializedName("page") val page: Int = 0,
                         @SerializedName("total_pages") val totalPages: Int = 0,
                         @SerializedName("results") val results: List<FilmItem>?,
                         @SerializedName("total_results") val totalResults: Int = 0)