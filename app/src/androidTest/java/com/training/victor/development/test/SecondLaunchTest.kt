package com.training.victor.development.test

import android.content.Intent
import android.os.AsyncTask
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.training.victor.development.R
import com.training.victor.development.assertions.RecyclerViewItemCountAssertion
import com.training.victor.development.ui.main.MainActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Rule

class SecondLaunchTest {
    @Rule
    val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)
    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {

//        //make espresso wait for RXJava
//        RxJavaPlugins.setIoSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
//        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
//        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
//
//        Intents.init()
//        mainActivityTestRule.launchActivity(Intent())
//        mainActivity = mainActivityTestRule.activity
    }

    @After
    fun tearDown() {
//        Intents.release()
//        mainActivity.finishAffinity()
    }


    // --------------------------------------------- TEST CASES ---------------------------------------------
//    @Given("^a user launch the app for first time")
//    fun a_user_launch_the_app_for_first_time() {
//        Assert.assertNotNull(mainActivity)
//    }

//    @When("main view screen is shown 2")
//    fun main_view_screen_is_shown_2() {
//        onView(withId(R.id.lstMovies))
//            .check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//        onView(withId(R.id.autoScrollViewPager))
//            .check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//    }
//
//    @And("both of movies list are fulfilled 2")
//    fun both_of_movies_list_are_fulfilled_2() {
//        onView(withId(R.id.lstMovies))
//            .check(RecyclerViewItemCountAssertion.withItemCount(Matchers.greaterThan(1)))
//        onView(withId(R.id.autoScrollViewPager)).perform(ViewActions.swipeLeft())
//    }
//
//    @And("^a movie from featured movies list is selected")
//    fun a_movie_from_featured_movies_list_is_selected() {
//        onView(withId(R.id.autoScrollViewPager)).perform(click())
//    }
//
//    @Then("I see the detail activity with all movie info 2")
//    fun i_see_the_detail_activity_with_all_movie_info_2() {
//        Thread.sleep(500)
//        onView(withId(R.id.imgDetail))
//            .check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//        pressBack()
//    }
}