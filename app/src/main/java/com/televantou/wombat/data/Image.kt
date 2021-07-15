package com.televantou.wombat.data

data class Image(
    val id: String,
    val resolutions: List<Source>,
    val source: Source
)