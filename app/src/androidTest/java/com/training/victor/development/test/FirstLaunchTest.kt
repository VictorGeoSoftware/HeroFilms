package com.training.victor.development.test

import android.content.Intent
import android.os.AsyncTask
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.training.victor.development.R
import com.training.victor.development.assertions.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.training.victor.development.ui.main.MainActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.Matchers.greaterThan
import org.junit.Assert
import org.junit.Rule

class FirstLaunchTest {
    @Rule val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)
    private lateinit var mainActivity: MainActivity

    @Before("@firstCase")
    fun setUp() {

        //make espresso wait for RXJava
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }

        Intents.init()
        mainActivityTestRule.launchActivity(Intent())
        mainActivity = mainActivityTestRule.activity
    }

    @After("@firstCase")
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
        onView(withId(R.id.lstMovies)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.autoScrollViewPager)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @And("both of movies list are fulfilled")
    fun both_of_movies_list_are_fulfilled() {
        onView(withId(R.id.lstMovies)).check(withItemCount(greaterThan(1)))
        onView(withId(R.id.autoScrollViewPager)).perform(swipeLeft())
    }

    @And("a movie from movies list is selected")
    fun a_movie_from_movies_list_is_selected() {
        onView(withId(R.id.lstMovies)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
    }

    @Then("I see the detail activity with all movie info")
    fun i_see_the_detail_activity_with_all_movie_info() {
        Thread.sleep(500)
        onView(withId(R.id.imgDetail)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        pressBack()
    }
}