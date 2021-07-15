package com.televantou.wombat.di

import com.televantou.wombat.api.RedditApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideService(): RedditApi {
        return RedditApi.create()
    }
}
