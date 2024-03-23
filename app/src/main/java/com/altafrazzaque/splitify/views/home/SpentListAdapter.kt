package com.altafrazzaque.splitify.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.altafrazzaque.splitify.databinding.ItemSpentByBinding
import com.altafrazzaque.splitify.model.ExpenseItem
import com.altafrazzaque.splitify.views.adapter.ClickListener
import com.altafrazzaque.splitify.views.adapter.ExpenseItemDiffCallback

import timber.log.Timber

class SpentListAdapter :
    ListAdapter<ExpenseItem, SpentListAdapter.ViewHolder>(ExpenseItemDiffCallback()) {

        companion object {
            private const val TAG = "SpentListAdapter"
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.tag(TAG).i("onCreateViewHolder")
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        Timber.tag(TAG).i("onBindViewHolder %s", item.item)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val binding: ItemSpentByBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExpenseItem) {
            binding.tvPaidBy.text = item.item
            binding.amount.text = "₹${item.price}"
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSpentByBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
