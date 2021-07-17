package com.televantou.wombat.ui.main

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.televantou.wombat.test.HiltTestActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import com.televantou.wombat.R
import com.televantou.wombat.waitUntilVisible

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.Spy

/**
 * Created by Eirini Televantou on 17/07/2021 for Wombat.
 */

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainFragmentTest { private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    @Spy
    var mainFragment: MainFragment? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        hiltRule.inject()
        mainFragment = MainFragment()
    }



    @Test
    fun getData() {

        launchFragmentInHiltContainer<MainFragment>(null, R.style.Theme_MaterialComponents)

        mainFragment!!.activity!!.runOnUiThread(Runnable {
            mainFragment?.getData()
        })
        Espresso.onView(ViewMatchers.withId(R.id.rclSubmissions)).perform(waitUntilVisible(7000L))

    }

    inline fun <reified T : Fragment> launchFragmentInHiltContainer(
        fragmentArgs: Bundle? = null,
        @StyleRes themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
        crossinline action: Fragment.() -> Unit = {}
    ) {
        val startActivityIntent = Intent.makeMainActivity(
            ComponentName(
                ApplicationProvider.getApplicationContext(),
                HiltTestActivity::class.java
            )
        ).putExtra(FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY, themeResId)

        ActivityScenario.launch<HiltTestActivity>(startActivityIntent).onActivity {
            mainFragment = it.supportFragmentManager.fragmentFactory.instantiate(
                Preconditions.checkNotNull(T::class.java.classLoader),
                T::class.java.name
            ) as MainFragment
            mainFragment!!.arguments = fragmentArgs
            it.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, mainFragment!!, "")
                .commitNow()

            mainFragment!!.action()
        }
    }


}