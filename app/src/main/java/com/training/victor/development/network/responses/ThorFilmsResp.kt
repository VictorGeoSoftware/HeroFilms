package com.training.victor.development.network.responses

import com.training.victor.development.network.responses.models.FilmItem

data class ThorFilmsResp(val page: Int = 0,
                         val totalPages: Int = 0,
                         val results: List<FilmItem>?,
                         val totalResults: Int = 0)