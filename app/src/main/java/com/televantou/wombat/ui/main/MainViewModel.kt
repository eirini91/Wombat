package com.televantou.asosspacex.ui.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.televantou.wombat.data.Submission
import com.televantou.wombat.data.repository.SubmissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Eirini Televantou on 15/02/2021 for ASOS.
 */


@HiltViewModel
class MainViewModel @Inject constructor(
        private val submissionRepository: SubmissionRepository
) : ViewModel() {

    val errorCompanyInfo: ObservableField<String> = ObservableField()
    val loadingCompanyInfo: ObservableBoolean = ObservableBoolean(true)


    fun getFavoriteMovies(): Flowable<PagingData<Submission>> {

        return submissionRepository
                .getSubmissions()
                .map { pagingData ->
                    loadingCompanyInfo.set(false)

                    errorCompanyInfo.set(null)
                    pagingData
                }
                .doOnError {
                    loadingCompanyInfo.set(false)
                    errorCompanyInfo.set(it.localizedMessage)
                }
    }


}