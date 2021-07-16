package com.televantou.wombat.data.dao

import androidx.paging.DataSource
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.televantou.wombat.data.local.SubmissionLocal

/**
 * Created by Eirini Televantou on 16/07/2021 for Wombat.
 */

@Dao
interface SubmissionDao {

    @Query("SELECT * FROM submission_table")
    fun getAllSubmissions(): List<SubmissionLocal>

    @Query("SELECT COUNT(*) FROM submission_table")
    fun countSubmissions(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tasks: List<SubmissionLocal>): List<Long>

    @Query("DELETE FROM submission_table")
    suspend fun deleteAll()
}