package com.televantou.wombat.data

data class Data(
    val after: String,
    val before: String,
    val children: List<Children>,
    val dist: Int
)