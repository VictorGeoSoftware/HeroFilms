package com.training.victor.development.test

import android.content.Intent
import android.os.AsyncTask
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.training.victor.development.ui.main.MainActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FirstLaunchTest {
    @Rule val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)
    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }

        Intents.init()
        mainActivityTestRule.launchActivity(Intent())
        mainActivity = mainActivityTestRule.activity
    }

    @After
    fun tearDown() {
        Intents.release()
        mainActivity.finishAffinity()
    }


    // --------------------------------------------- TEST CASES ---------------------------------------------
    @Given("^a user launch the app for first time")
    fun a_user_launch_the_app_for_first_time() {
        Assert.assertNotNull(mainActivity)
    }

    @When("main view screen is shown")
    fun main_view_screen_is_shown() {
//        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

    }

    @And("movies list is shown")
    fun movies_list_is_shown() {
//        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @And("featured movies list is shown")
    fun featured_movies_list_is_shown() {
//        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Then("both of movies list are fulfilled")
    fun both_of_movies_list_are_fulfilled() {
//        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
//        onView(withId(R.id.lstMovies)).check(withItemCount(greaterThan(0)))
    }
}