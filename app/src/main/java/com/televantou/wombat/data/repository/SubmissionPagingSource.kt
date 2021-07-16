package com.televantou.wombat.data.repository


import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.televantou.wombat.api.RedditApi
import com.televantou.wombat.data.dao.SubmissionDao
import com.televantou.wombat.data.local.SubmissionLocal
import com.televantou.wombat.data.net.SubmissionsResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */
const val UNSPLASH_STARTING_PAGE_INDEX = 20

//Paging source class for paging functionality when loading Submissions
class SubmissionPagingSource(val service: RedditApi,val mapper: SubmissionsMapper, val submissionDao: SubmissionDao) :
        RxPagingSource<Int, SubmissionLocal>() {

    var lastAfterValue : String? = null

    override fun getRefreshKey(state: PagingState<Int, SubmissionLocal>): Int? {
        return UNSPLASH_STARTING_PAGE_INDEX
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, SubmissionLocal>> {
        val page = params.key ?: 1

        return service.getSubmissions(UNSPLASH_STARTING_PAGE_INDEX, page, lastAfterValue)
                .subscribeOn(Schedulers.io())
                .doOnSuccess {
                    toLoadResult(mapAndStore(it), page)}
                .map { mapAndStore(it) }
                .map { toLoadResult(it, page) }
                .onErrorReturn {
                    LoadResult.Error(it)
                }

    }


    fun mapAndStore(list: SubmissionsResponse): List<SubmissionLocal> {
        lastAfterValue = list.data.after
        var list = mapper.transform(list)
        submissionDao.insert( list)
        return list
    }


    private fun toLoadResult(
            data: List<SubmissionLocal>,
            position: Int
    ): LoadResult<Int, SubmissionLocal> {
        return LoadResult.Page(
                data = data,
                prevKey =null,
                nextKey = if (data.size < UNSPLASH_STARTING_PAGE_INDEX || data.isEmpty()) null else position + UNSPLASH_STARTING_PAGE_INDEX
        )
    }



}

