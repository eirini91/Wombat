package com.televantou.wombat.di

import android.content.Context
import com.televantou.wombat.data.dao.SubmissionDao
import com.televantou.wombat.db.RedditDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */

//Task module to be injected using Dagger
@InstallIn(SingletonComponent::class)
@Module
class SubmissionDaoModule {

    @Singleton
    @Provides
    fun provideDao(db : RedditDB): SubmissionDao {
        return db.sampleDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): RedditDB {
        return RedditDB.getDatabase(appContext)
    }
}
