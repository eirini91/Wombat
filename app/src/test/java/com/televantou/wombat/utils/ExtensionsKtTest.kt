package com.televantou.wombat.utils

import com.televantou.wombat.submission
import com.televantou.wombat.submissionNegative
import com.televantou.wombat.submissionZero
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Eirini Televantou on 17/07/2021 for Wombat.
 */
@RunWith(JUnit4::class)
class ExtensionsKtTest : TestCase() {

    @Test
    fun testGetCountPositiveNumberReturnsPositive() {
        val counter = submission.getCount()
        assertEquals(counter,"5")
    }

    @Test
    fun testGetCountNegativeNumberReturnsNegative() {
        val counter = submissionNegative.getCount()
        assertEquals(counter,"-5")
    }

    @Test
    fun testGetCountZeroNumberReturnsZero() {
        val counter = submissionZero.getCount()
        assertEquals(counter,"0")
    }

    @Test
    fun testGetRedditUrl() {
        val url = submission.getRedditUrl()
        assertEquals(url,"https://www.reddit.comblahblahblah")
    }
}