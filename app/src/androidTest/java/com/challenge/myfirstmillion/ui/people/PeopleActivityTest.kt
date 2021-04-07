package com.challenge.myfirstmillion.ui.people

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.challenge.myfirstmillion.R
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
//TODO inject mocked datasource for this test! -> https://developer.android.com/training/dependency-injection/hilt-testing
//Mocking datasource make posible to develop and use the same assertions each time!
class PeopleActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(PeopleActivity::class.java)

    @Test
            /**
             * TODO All Sleeps should be replacesd
             * // Added a sleep statement to match the app's execution delay.
             * // The recommended way to handle such scenarios is to use Espresso idling resources:
             * // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
             */
    fun test_edition() {
        Thread.sleep(2000)

        val rv = onView(withId(R.id.rvPeople))
        rv.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                RvViewAction.clickChildViewWithId(R.id.btnEdit)
            )
        )

        Thread.sleep(1000)

        val editTextView = onView(withId(R.id.etvHourlyWage))
        editTextView.perform(ViewActions.replaceText("32"))

        Thread.sleep(1000)

        editTextView.perform(ViewActions.pressImeActionButton())

        Thread.sleep(1000)
        /*

        //Assertions!

         val viewGroup = onView(
            allOf(
                withParent(
                    allOf(
                        withId(R.id.card),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.tvTimeRequired), withText("75 years, 2 months"),
                withParent(withParent(withId(R.id.card))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("75 years, 2 months")))
         */
    }

    object RvViewAction {
        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<View>(id)
                    v.performClick()
                }
            }
        }
    }
}