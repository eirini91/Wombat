package com.televantou.wombat.ui.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.televantou.wombat.data.Submission
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
    val loading: ObservableBoolean = ObservableBoolean(true)

    fun getFavoriteMovies(): Flowable<PagingData<Submission>> {

        return submissionRepository
                .getSubmissions()
                .map { pagingData ->
                    loading.set(false)
                    error.set(null)
                    pagingData
                }
                .doOnError {
                    loading.set(false)
                    error.set(it.localizedMessage)
                }
    }


}