package com.training.victor.development.network.responses

import com.training.victor.development.network.responses.models.*

data class FilmDetailResp(val originalLanguage: String = "",
                          val imdbId: String = "",
                          val video: Boolean = false,
                          val title: String = "",
                          val backdropPath: String = "",
                          val revenue: Int = 0,
                          val genres: List<GenresItem>?,
                          val popularity: Double = 0.0,
                          val productionCountries: List<ProductionCountriesItem>?,
                          val id: Int = 0,
                          val voteCount: Int = 0,
                          val budget: Int = 0,
                          val overview: String = "",
                          val originalTitle: String = "",
                          val runtime: Int = 0,
                          val posterPath: String = "",
                          val spokenLanguages: List<SpokenLanguagesItem>?,
                          val productionCompanies: List<ProductionCompaniesItem>?,
                          val releaseDate: String = "",
                          val voteAverage: Double = 0.0,
                          val belongsToCollection: BelongsToCollection,
                          val tagline: String = "",
                          val adult: Boolean = false,
                          val homepage: String = "",
                          val status: String = "")