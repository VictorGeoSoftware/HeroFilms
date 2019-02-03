package com.training.victor.development

import com.nhaarman.mockito_kotlin.*
import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.network.ThorFilmsRepository
import com.training.victor.development.network.responses.ThorFilmsResp
import com.training.victor.development.presenter.ThorFilmsPresenter
import com.training.victor.development.utils.getMockedMoviesList
import com.training.victor.development.utils.getMockedThorMoviesResp
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ThorFilmsPresenterTest: ParentUnitTest() {

    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var thorFilmsRepository: ThorFilmsRepository
    @Mock lateinit var thorFilmsView: ThorFilmsPresenter.ThorFilmsView

    private lateinit var testScheduler: TestScheduler
    private lateinit var thorFilmsPresenter: ThorFilmsPresenter

    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        thorFilmsPresenter = createMockedPresenter()
    }

    private fun createMockedPresenter(): ThorFilmsPresenter {
        val profilesPresenter = ThorFilmsPresenter(testScheduler, testScheduler, dataManager)
        profilesPresenter.view = thorFilmsView
        return profilesPresenter
    }


    // --------------------------------------------- TESTING CASES ---------------------------------------------
    @Test
    fun `should call to profiles list and retrieve a list`() {
        val heroName = BuildConfig.APP_HERO
        val appLang = BuildConfig.APP_LANGUAGE
        val apiKey = BuildConfig.API_KEY

        val thorFilmsResp = getMockedThorMoviesResp()
        val moviesArrayList = getMockedMoviesList()

        whenever(thorFilmsRepository.getThorMovies(apiKey, heroName, appLang)).thenReturn(Observable.just(thorFilmsResp))
        thorFilmsPresenter.getMoviesList()
        testScheduler.triggerActions()


        verify(thorFilmsView, times(1)).onMoviesListReceived(moviesArrayList)
    }

    @Test
    fun `should call to profiles list and retrieve an error`() {
        val heroName = BuildConfig.APP_HERO
        val appLang = BuildConfig.APP_LANGUAGE
        val apiKey = BuildConfig.API_KEY
        whenever(thorFilmsRepository.getThorMovies(apiKey, heroName, appLang)).thenReturn(Observable.error(Throwable()))
        thorFilmsPresenter.getMoviesList()
        testScheduler.triggerActions()

        verify(thorFilmsView, times(1)).onMoviesListError()
    }
}
