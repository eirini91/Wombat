package com.televantou.wombat.utils

import com.televantou.wombat.data.Submission

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */
fun Submission.getCount(): String {
    return (this.data.ups - this.data.downs).toString()
}

fun Submission.getRedditUrl(): String {
    return "https://www.reddit.com" + this.data.permalink
}