package com.training.victor.development

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.training.victor.development.data.DataManager
import com.training.victor.development.data.toMovieDetailItem
import com.training.victor.development.network.ThorFilmsRepository
import com.training.victor.development.presenter.ThorFilmsPresenter
import com.training.victor.development.utils.getMockedMovieDetailResp
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
import java.util.concurrent.TimeoutException
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
    fun `should call to movies api and retrieve a list`() {
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
    fun `should call to movies api and retrieve an error`() {
        val heroName = BuildConfig.APP_HERO
        val appLang = BuildConfig.APP_LANGUAGE
        val apiKey = BuildConfig.API_KEY
        whenever(thorFilmsRepository.getThorMovies(apiKey, heroName, appLang)).thenReturn(Observable.error(Throwable()))
        thorFilmsPresenter.getMoviesList()
        testScheduler.triggerActions()

        verify(thorFilmsView, times(1)).onMoviesListError()
    }

    @Test
    fun `should call to movies api and retrieve the most featured movies`() {
        val heroName = BuildConfig.APP_HERO
        val appLang = BuildConfig.APP_LANGUAGE
        val apiKey = BuildConfig.API_KEY
        val response = getMockedThorMoviesResp()

        whenever(thorFilmsRepository.getThorMovies(apiKey, heroName, appLang)).thenReturn(Observable.just(response))
        thorFilmsPresenter.getFeaturedMoviesList()
        testScheduler.triggerActions()

        val featuredMoviesList = getMockedMoviesList()
        verify(thorFilmsView, times(1)).onFeaturedMoviesReceived(featuredMoviesList)
    }

    @Test
    fun `should call to movie detail and retrieve the corresponding movie detail info`() {
        val movieResp = getMockedMovieDetailResp()
        val apiKey = BuildConfig.API_KEY
        val movieId = 10195

        whenever(thorFilmsRepository.getMovieDetail(movieId, apiKey)).thenReturn(Observable.just(movieResp))
        thorFilmsPresenter.getMovieDetails(movieId)
        testScheduler.triggerActions()

        val movieDetails = movieResp.toMovieDetailItem()
        verify(thorFilmsView, times(1)).onMovieDetailsReceived(movieDetails)
    }

    @Test
    fun `should call to movie detail and retrieve some error`() {
        val throwable = Throwable(TimeoutException())
        val apiKey = BuildConfig.API_KEY
        val movieId = 10195

        whenever(thorFilmsRepository.getMovieDetail(movieId, apiKey)).thenReturn(Observable.error(throwable))
        thorFilmsPresenter.getMovieDetails(movieId)
        testScheduler.triggerActions()

        verify(thorFilmsView, times(1)).onMovieDetailsError(throwable)
    }
}
