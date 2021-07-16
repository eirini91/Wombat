package com.televantou.wombat.data.repository

import com.televantou.wombat.data.local.SubmissionLocal
import com.televantou.wombat.data.net.SubmissionsResponse
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Eirini Televantou on 16/07/2021 for Wombat.
 */
class SubmissionsMapper {


    fun transform(response: SubmissionsResponse): List<SubmissionLocal> {
        return with(response.data.children) {
            ArrayList<SubmissionLocal>(this.map {
                SubmissionLocal(
                    it.data.author,
                    it.data.downs,
                    it.data.id,
                    it.data.name,
                    it.data.num_comments,
                    it.data.thumbnail,
                    it.data.title,
                    it.data.ups,
                    it.data.permalink
                )
            }
            )
        }
    }
}
