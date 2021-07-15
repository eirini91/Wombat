package com.televantou.wombat.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.televantou.wombat.data.Submission
import com.televantou.wombat.databinding.MainFragmentBinding
import com.televantou.wombat.utils.getRedditUrl
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */

@AndroidEntryPoint
class MainFragment : Fragment(),
        LaunchAdapter.OnItemClickListener {

    private val viewModel: MainViewModel by viewModels()

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mDisposable = CompositeDisposable()

    var launchAdapter: LaunchAdapter = LaunchAdapter(this)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        val binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = (viewModel)
        binding.launchAdapter = launchAdapter

        getData()
        return binding.root
    }


    private fun getData() {
        mDisposable.add(viewModel.getFavoriteMovies().subscribe {
            launchAdapter.submitData(lifecycle, it)
        })
    }


    override fun onItemClicked(submission: Submission) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(submission.getRedditUrl())
        startActivity(i)
    }


}

