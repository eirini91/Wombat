package com.televantou.wombat.api

import com.televantou.wombat.BuildConfig
import com.televantou.wombat.data.SubmissionsResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */
interface RedditApi {

    @GET("hot/.json")
    fun getSubmissions(
            @Query("limit") limit: Int,
            @Query("count") page: Int
    ): Single<SubmissionsResponse>

    companion object {
        private const val BASE_URL = "https://www.reddit.com/r/Android/"

        //log everything if we are running from Android studio or using a debugg version of the app
        private val logger = HttpLoggingInterceptor().apply {
            level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        fun create(): RedditApi {

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RedditApi::class.java)
        }
    }
}