package com.televantou.wombat.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.televantou.wombat.api.RedditApi
import com.televantou.wombat.data.Submission
import com.televantou.wombat.data.SubmissionsResponse
import io.reactivex.Flowable
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */
class SubmissionRepository @Inject constructor(private val service: RedditApi) {


     fun getSubmissions(): Flowable<PagingData<Submission>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { SubmissionPagingSource(service) }
        ).flowable
    }
}