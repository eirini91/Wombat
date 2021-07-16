package com.televantou.wombat.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.televantou.wombat.data.net.Submission
import com.televantou.wombat.data.dao.SubmissionDao
import com.televantou.wombat.data.local.SubmissionLocal

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */

@Database(entities = arrayOf(SubmissionLocal::class), version = 1, exportSchema = false)
abstract class RedditDB : RoomDatabase() {
    abstract fun sampleDao(): SubmissionDao

    companion object {
        @Volatile
        private var INSTANCE: RedditDB? = null

        fun getDatabase(context: Context): RedditDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RedditDB::class.java,
                        "reddit_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}