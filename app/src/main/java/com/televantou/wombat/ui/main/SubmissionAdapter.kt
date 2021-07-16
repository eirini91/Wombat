package com.televantou.wombat.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.televantou.wombat.data.local.SubmissionLocal
import com.televantou.wombat.databinding.SubmissionRowBinding
import com.televantou.wombat.utils.getCount

/**
 * Created by Eirini Televantou on 15/07/2021 for Wombat.
 */

class SubmissionAdapter(
        private var onItemClickListener: OnItemClickListener
) : PagingDataAdapter<SubmissionLocal, SubmissionAdapter.ViewHolder>(SubmissionComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SubmissionRowBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.item = currentItem
        holder.binding.executePendingBindings()
        currentItem?.let { holder.bind(it) }
    }

    inner class ViewHolder(val binding: SubmissionRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SubmissionLocal) {
            binding.txtUpvotes.text = item.getCount()
            binding.parent.setOnClickListener { onItemClickListener.onItemClicked(item) }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(submission: SubmissionLocal)
    }

    object SubmissionComparator : DiffUtil.ItemCallback<SubmissionLocal>() {
        override fun areItemsTheSame(oldItem: SubmissionLocal, newItem: SubmissionLocal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SubmissionLocal, newItem: SubmissionLocal): Boolean {
            return oldItem == newItem
        }
    }
}