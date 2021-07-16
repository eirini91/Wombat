package com.televantou.wombat.utils

import com.televantou.wombat.data.local.SubmissionLocal
import com.televantou.wombat.data.net.Submission

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */
fun SubmissionLocal.getCount(): String {
    return (this.ups - this.downs).toString()
}

fun SubmissionLocal.getRedditUrl(): String {
    return "https://www.reddit.com" + this.permalink
}