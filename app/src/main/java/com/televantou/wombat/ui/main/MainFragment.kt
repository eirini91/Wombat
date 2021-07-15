package com.televantou.wombat.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.televantou.asosspacex.ui.main.LaunchAdapter
import com.televantou.asosspacex.ui.main.MainViewModel
import com.televantou.wombat.data.Submission
import com.televantou.wombat.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Job


/**
 * Created by Eirini Televantou on 15/02/2021 for ASOS.
 */

@AndroidEntryPoint
class MainFragment : Fragment(),
    LaunchAdapter.OnItemClickListener {


    companion object {
        fun newInstance() = MainFragment()
    }

    private val mDisposable = CompositeDisposable()

    var launchAdapter: LaunchAdapter = LaunchAdapter(context, this)
    private val viewModel: MainViewModel by viewModels()


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
        // Make sure we cancel the previous job before creating a new one
        mDisposable.add(viewModel.getFavoriteMovies().subscribe {
            launchAdapter.submitData(lifecycle, it)
        })
    }


    override fun onItemClicked(launchItem: Submission) {
        //launch reddit
    }


}

