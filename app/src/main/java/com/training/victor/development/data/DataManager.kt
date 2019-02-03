package com.training.victor.development.data

import com.training.victor.development.BuildConfig
import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.network.ThorFilmsRepository
import io.reactivex.Observable

class DataManager(private val thorFilmsRepository: ThorFilmsRepository) {

    fun getMoviesList(): Observable<List<MovieItem>> {
        return thorFilmsRepository.getThorMovies(BuildConfig.API_KEY, BuildConfig.APP_HERO, BuildConfig.APP_LANGUAGE)
            .flatMapIterable {response ->
                response.results
            }
            .map {filmItem ->
                filmItem.toMovieItem()
            }
            .toList().toObservable()
    }


}