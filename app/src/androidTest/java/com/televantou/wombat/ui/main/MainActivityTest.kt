package com.televantou.wombat.ui.main

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.televantou.wombat.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Eirini Televantou on 17/07/2021 for Wombat.
 */
@HiltAndroidTest
@LargeTest
@RunWith(JUnit4::class)
class MainActivityTest {

    private val hiltRule = HiltAndroidRule(this)

    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(mActivityTestRule)

    @Test
    fun mainActivityTest() {
        val textView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withText("Wombat"),
                ViewMatchers.withParent(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.action_bar),
                        ViewMatchers.withParent(ViewMatchers.withId(R.id.action_bar_container))
                    )
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView.check(ViewAssertions.matches(ViewMatchers.withText("Wombat")))
    }
}