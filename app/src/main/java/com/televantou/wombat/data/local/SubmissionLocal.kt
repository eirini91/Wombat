package com.televantou.wombat.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "submission_table")
data class SubmissionLocal(
    val author: String,
    val downs: Int,
    @PrimaryKey val id: String,
    val name: String,
    val num_comments: Int,
    val thumbnail: String,
    val title: String,
    val ups: Int,
    val permalink: String
)