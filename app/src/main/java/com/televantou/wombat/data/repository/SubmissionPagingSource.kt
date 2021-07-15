package com.televantou.wombat.data.repository


import androidx.paging.*
import androidx.paging.rxjava2.RxPagingSource
import com.televantou.wombat.api.RedditApi
import com.televantou.wombat.data.Submission
import com.televantou.wombat.data.SubmissionsResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */
private const val UNSPLASH_STARTING_PAGE_INDEX = 15

//Paging source class for paging functionality when loading Submissions
class SubmissionPagingSource(val service: RedditApi) :
    RxPagingSource<Int, Submission>() {


    override fun getRefreshKey(state: PagingState<Int, Submission>): Int? {
        return 20
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Submission>> {
        val page = params.key ?: 1

        return service.getSubmissions(
            20,
            page
        )
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }

    }

    private fun toLoadResult(
        data: SubmissionsResponse,
        position: Int
    ): LoadResult<Int, Submission> {
        return LoadResult.Page(
            data = data.data.children,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (data.data.children.size < UNSPLASH_STARTING_PAGE_INDEX || data.data.children.isEmpty()) null else position + 1
        )
    }

}