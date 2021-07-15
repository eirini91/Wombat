package com.televantou.wombat.data

data class DataX(
    val author: String,
    val author_fullname: String,
    val created: Int,
    val created_utc: Int,
    val downs: Int,
    val hide_score: Boolean,
    val id: String,
    val is_reddit_media_domain: Boolean,
    val is_robot_indexable: Boolean,
    val is_self: Boolean,
    val is_video: Boolean,
    val media_only: Boolean,
    val name: String,
    val num_comments: Int,
    val preview: Preview,
    val thumbnail: String,
    val title: String,
    val total_awards_received: Int,
    val ups: Int,
    val url: String,
    val url_overridden_by_dest: String
)