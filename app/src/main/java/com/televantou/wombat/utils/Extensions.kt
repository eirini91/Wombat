package com.televantou.wombat.utils

import com.televantou.wombat.data.Submission

/**
 * Created by Eirini Televantou on 15/07/2021 for iPlato.
 */
fun Submission.getCount(): String {
    if (this == null || this.data == null || this.data.ups == null || this.data.downs == null)
        return "0"
    return (this.data.ups - this.data.downs).toString()
}