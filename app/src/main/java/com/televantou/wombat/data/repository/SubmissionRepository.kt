package com.televantou.wombat.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.rxjava2.flowable
import com.televantou.wombat.api.RedditApi
import com.televantou.wombat.data.dao.SubmissionDao
import com.televantou.wombat.data.local.SubmissionLocal
import com.televantou.wombat.data.net.Submission
import io.reactivex.Flowable
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */
class SubmissionRepository @Inject constructor(private val service: RedditApi, private val mapper: SubmissionsMapper, private val submissionDao: SubmissionDao) {

    val submissionPagingSource =  SubmissionPagingSource(service, mapper, submissionDao)

    fun getSubmissions(): Flowable<PagingData<SubmissionLocal>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                prefetchDistance = 5,
                initialLoadSize = 20),
            pagingSourceFactory = {submissionPagingSource }
        ).flowable
    }

    fun loadFromDb(): List<SubmissionLocal> =
        submissionDao.getAllSubmissions()
}