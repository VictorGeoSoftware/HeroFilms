package com.training.victor.development.presenter

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.ProfileItem
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ThorFilmsPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                             private val subscriberSchedulers: Scheduler,
                                             private val dataManager: DataManager): Presenter<ThorFilmsPresenter.ThorFilmsView>() {

    private val compositeDisposable = CompositeDisposable()

    interface ThorFilmsView {
        fun showProgressBar(show: Boolean)
        fun onProfilesListReceived(profilesList: ArrayList<ProfileItem>)
        fun onProfilesListError()
    }


    /*
    TODO

    - super hero: Thor -> marketing rules :)
    - Get Thor films list. Get ensured that more than 10 items are retrieved
    - Get a complete list, and retrieve a "most featured films" one from it


     - Learn about the Builder pattern in Kotlin
            -> https://stackoverflow.com/questions/45604789/builder-pattern-in-kotlin
     - Strong and straight with Cucumber, all in with it!!
     */

    fun getProfilesList() {
        view?.showProgressBar(true)
        compositeDisposable.add(dataManager.getMoviesList()
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe ({
                view?.showProgressBar(false)
                view?.onProfilesListReceived(it)
            }, {
                view?.showProgressBar(false)
                view?.onProfilesListError()
            }))

    }

    override fun destroy() {
        super.destroy()
        compositeDisposable.clear()
    }
}