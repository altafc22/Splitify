package com.altafrazzaque.splitify.views.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.altafrazzaque.splitify.databinding.ItemHistoryBinding
import com.altafrazzaque.splitify.model.ExpenseItem
import com.altafrazzaque.splitify.views.adapter.ExpenseItemDiffCallback
import formatDate

class ExpenseHistoryListAdapter :
    ListAdapter<ExpenseItem, ExpenseHistoryListAdapter.ViewHolder>(ExpenseItemDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExpenseItem) {
            binding.title.text = item.item
            binding.tvPrice.text = "${item.price}$"
            binding.tvDate.text = item.date.formatDate()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHistoryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}