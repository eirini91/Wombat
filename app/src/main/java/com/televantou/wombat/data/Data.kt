package com.televantou.wombat.data

data class Data(
    val after: String,
    val before: String,
    val children: List<Submission>,
    val dist: Int
)