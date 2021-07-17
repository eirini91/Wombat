package com.televantou.wombat.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.televantou.wombat.data.local.SubmissionLocal
import com.televantou.wombat.databinding.SubmissionRowBinding
import com.televantou.wombat.utils.getCount

/**
 * Created by Eirini Televantou on 16/07/2021 for Wombat.
 */

class SubmissionLocalAdapter(
    private var items: List<SubmissionLocal>,
    private var onItemClickListener: SubmissionAdapter.OnItemClickListener
) : RecyclerView.Adapter<SubmissionLocalAdapter.ViewHolder>() {

    fun set(newItems: List<SubmissionLocal>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SubmissionRowBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.binding.item = currentItem
        holder.binding.executePendingBindings()
        holder.binding.txtUpvotes.text = currentItem.getCount()
        holder.binding.parent.setOnClickListener { onItemClickListener.onItemClicked(currentItem) }
    }

    inner class ViewHolder(val binding: SubmissionRowBinding) :
        RecyclerView.ViewHolder(binding.root)
}