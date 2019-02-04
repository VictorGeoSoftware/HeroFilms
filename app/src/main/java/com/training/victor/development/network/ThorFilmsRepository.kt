package com.training.victor.development.network

import com.training.victor.development.network.responses.FilmDetailResp
import com.training.victor.development.network.responses.ThorFilmsResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ThorFilmsRepository {
    @Headers("Content-Type: application/json;charset=UTF-8")

    @GET("/3/search/movie")
    fun getThorMovies(@Query("api_key") apiKey: String,
                      @Query("query") query: String,
                      @Query("language") language: String): Observable<ThorFilmsResp>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int,
                       @Query("api_key") apiKey: String): Observable<FilmDetailResp>
}