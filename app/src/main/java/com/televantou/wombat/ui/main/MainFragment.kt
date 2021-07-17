package com.televantou.wombat.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.televantou.wombat.R
import com.televantou.wombat.data.local.SubmissionLocal
import com.televantou.wombat.databinding.MainFragmentBinding
import com.televantou.wombat.utils.Utils
import com.televantou.wombat.utils.getRedditUrl
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */

@AndroidEntryPoint
class MainFragment : Fragment(),
    SubmissionAdapter.OnItemClickListener {

    private val viewModel: MainViewModel by viewModels()
    private val mDisposable = CompositeDisposable()

    lateinit var binding: MainFragmentBinding
    var submissionAdapter: SubmissionAdapter = SubmissionAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = (viewModel)
        setupAdapter()
        getData()
        return binding.root
    }

    private fun setupAdapter() {
        submissionAdapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error
            errorState?.let {
                viewModel.error.set(it.error.localizedMessage)
            }
        }
    }

    fun getData() {
        if (Utils().isNetworkAvailable(requireContext())) {
            binding.rclSubmissions.adapter = submissionAdapter
            mDisposable.add(viewModel.getSubmissions().subscribe {
                submissionAdapter.submitData(lifecycle, it)
            })
        } else {

            // using a recycler view adapter for when we don't have connection. This is a bad practice.
            // I only did this as I could not in the 5 hour timeframe create an RxRemoteMediator to return the db results and network results using RXJava (as the library was created to be used by coroutines
            val submissionLocalAdapter = SubmissionLocalAdapter(emptyList(), this)
            binding.rclSubmissions.adapter = submissionLocalAdapter
            Thread {
                submissionLocalAdapter.set(viewModel.loadSubmissions())
            }.start()
            Toast.makeText(context, getString(R.string.no_internet_toast), LENGTH_LONG).show()

        }
    }


    override fun onItemClicked(submission: SubmissionLocal) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(submission.getRedditUrl())
        startActivity(i)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

