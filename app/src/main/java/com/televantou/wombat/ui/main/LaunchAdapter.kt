package com.televantou.wombat.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.televantou.wombat.data.Submission
import com.televantou.wombat.databinding.LaunchRowBinding
import com.televantou.wombat.utils.getCount

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */

class LaunchAdapter(
        private var onItemClickListener: OnItemClickListener
) : PagingDataAdapter<Submission, LaunchAdapter.ViewHolder>(UserComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LaunchRowBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.item = currentItem
        holder.binding.executePendingBindings()
        currentItem?.let { holder.bind(it) }
    }

    inner class ViewHolder(val binding: LaunchRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Submission) {
            binding.txtUpvotes.text = item.getCount()
            binding.parent.setOnClickListener { onItemClickListener.onItemClicked(item) }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(launchItem: Submission)
    }

    object UserComparator : DiffUtil.ItemCallback<Submission>() {
        override fun areItemsTheSame(oldItem: Submission, newItem: Submission): Boolean {
            return oldItem.data.id == newItem.data.id
        }

        override fun areContentsTheSame(oldItem: Submission, newItem: Submission): Boolean {
            return oldItem == newItem
        }
    }
}