package com.training.victor.development.presenter

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.utils.myTrace
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ThorFilmsPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                             private val subscriberSchedulers: Scheduler,
                                             private val dataManager: DataManager): Presenter<ThorFilmsPresenter.ThorFilmsView>() {

    private val compositeDisposable = CompositeDisposable()

    interface ThorFilmsView {
        fun showProgressBar(show: Boolean)
        fun onMoviesListReceived(profilesList: List<MovieItem>)
        fun onMoviesListError()
    }



    // todo :: retrieve the 5 more rated films

    // todo :: implement detail activity
        // - presenter and unit testing!
        // - UI
        // - Transition effects!

    // todo :: start with cucumber testing scenarios

    fun getFeaturedMoviesList() {
        view?.showProgressBar(true)
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
                myTrace("getMoviesList - error :: ${it.message}")
                it.printStackTrace()
                view?.showProgressBar(false)
                view?.onMoviesListError()
            }))

    }

    override fun destroy() {
        super.destroy()
        compositeDisposable.clear()
    }
}