package com.training.victor.development

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.ProfileItem
import com.training.victor.development.network.ThorFilmsRepository
import com.training.victor.development.presenter.ThorFilmsPresenter
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
        whenever(thorFilmsRepository.getThorMovies()).thenReturn(Observable.just(arrayListOf(ProfileItem(0, "hhttps"))))
        thorFilmsPresenter.getProfilesList()
        testScheduler.triggerActions()

        verify(thorFilmsView, times(1)).onProfilesListReceived(any())
    }

    @Test
    fun `should call to profiles list and retrieve an error`() {
        whenever(thorFilmsRepository.getThorMovies()).thenReturn(Observable.error(Throwable()))
        thorFilmsPresenter.getProfilesList()
        testScheduler.triggerActions()

        verify(thorFilmsView, times(1)).onProfilesListError()
    }
}
