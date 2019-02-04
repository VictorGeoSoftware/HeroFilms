package com.training.victor.development.presenter

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.MovieDetailItem
import com.training.victor.development.data.models.MovieItem
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ThorFilmsPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                             private val subscriberSchedulers: Scheduler,
                                             private val dataManager: DataManager): Presenter<ThorFilmsPresenter.ThorFilmsView>() {

    private val compositeDisposable = CompositeDisposable()

    interface ThorFilmsView {
        fun showProgressBar(show: Boolean)
        fun onMoviesListReceived(moviesList: List<MovieItem>)
        fun onMoviesListError()
        fun onFeaturedMoviesReceived(featuredMoviesList: List<MovieItem>)
        fun onFeaturedMoviesError()
        fun onMovieDetailsReceived(movieDetails: MovieDetailItem)
        fun onMovieDetailsError(throwable: Throwable)
    }


    // todo :: implement detail activity
        // - UI
        // - Transition effects!

    // todo :: start with cucumber testing scenarios


    fun getFeaturedMoviesList() {
        view?.showProgressBar(true)

        compositeDisposable.add(dataManager.getFeaturedMoviesList()
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.showProgressBar(false)
                view?.onFeaturedMoviesReceived(it)
            }, {
                it.printStackTrace()
                view?.showProgressBar(false)
                view?.onFeaturedMoviesError()
            }))
    }

    fun getMoviesList() {
        view?.showProgressBar(true)
        compositeDisposable.add(dataManager.getMoviesList()
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe ({
                view?.showProgressBar(false)
                view?.onMoviesListReceived(it)
            }, {
                it.printStackTrace()
                view?.showProgressBar(false)
                view?.onMoviesListError()
            }))

    }

    fun getMovieDetails(movieId: Int) {
        view?.showProgressBar(true)

        compositeDisposable.add(dataManager.getMovieDetails(movieId)
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.showProgressBar(false)
                view?.onMovieDetailsReceived(it)
            },{
                it.printStackTrace()
                view?.showProgressBar(false)
                view?.onMovieDetailsError(it)
            }))
    }

    override fun destroy() {
        super.destroy()
        compositeDisposable.clear()
    }
}