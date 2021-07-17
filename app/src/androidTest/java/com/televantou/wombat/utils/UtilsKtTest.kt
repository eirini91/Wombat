package com.televantou.wombat.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.televantou.wombat.utils.Utils
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Eirini Televantou on 17/07/2021 for Wombat.
 */


@RunWith(MockitoJUnitRunner::class)
class UtilsKtTest {
    @Mock
    lateinit var connectivityManager: ConnectivityManager

    @Mock
    lateinit var capabilities: NetworkCapabilities

    lateinit var utils: Utils

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        utils = Utils()
    }



    @Test
    fun getActiveNetworkInfo_fullConnectivity_shouldReturnTrueCorrectly() {

        Mockito.`when`(connectivityManager.getNetworkCapabilities(any())).thenReturn(
            capabilities
        )
        Mockito.`when`(capabilities.hasTransport(anyInt())).thenReturn(
            true
        )

        Assert.assertEquals(utils.isNetworkAvailable(connectivityManager), true)

    }


    @Test
    fun getActiveNetworkInfo_NoConnectivity_shouldReturnFalseCorrectly() {

        Mockito.`when`(connectivityManager.getNetworkCapabilities(any())).thenReturn(
            null
        )

        Assert.assertEquals(utils.isNetworkAvailable(connectivityManager), false)

    }
}