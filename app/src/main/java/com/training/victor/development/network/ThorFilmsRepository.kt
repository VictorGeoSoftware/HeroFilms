package com.training.victor.development.network

import com.training.victor.development.network.responses.FilmDetailResp
import com.training.victor.development.network.responses.ProfileDetailResp
import com.training.victor.development.network.responses.ThorFilmsResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ThorFilmsRepository {
    @Headers("Content-Type: application/json;charset=UTF-8")

    @GET("/search/movie")
    fun getThorMovies(@Query("query") query: String,
                      @Query("language") language: String): Observable<ThorFilmsResp>

    @GET("/movie/{movie_id}")
    fun getMovieDetail(@Path("id") id: Int,
                       @Query("api_key") apiKey: String): Observable<FilmDetailResp>
}