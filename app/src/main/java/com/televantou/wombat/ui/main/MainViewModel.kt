package com.televantou.wombat.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.televantou.wombat.data.local.SubmissionLocal
import com.televantou.wombat.data.net.Submission
import com.televantou.wombat.data.repository.SubmissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
        private val submissionRepository: SubmissionRepository
) : ViewModel() {

    val error: ObservableField<String> = ObservableField()

    fun getSubmissions(): Flowable<PagingData<SubmissionLocal>> {

        return submissionRepository
                .getSubmissions()
                .map { pagingData ->
                    error.set(null)
                    pagingData
                }
    }

    fun loadSubmissions(): List<SubmissionLocal> {
        return submissionRepository.loadFromDb()
    }


}