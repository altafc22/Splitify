package com.altafrazzaque.splitify.views.adapter

import androidx.recyclerview.widget.DiffUtil
import com.altafrazzaque.splitify.model.ExpenseItem
import javax.inject.Inject

class ExpenseItemDiffCallback : DiffUtil.ItemCallback<ExpenseItem>() {

    override fun areItemsTheSame(oldItem: ExpenseItem, newItem: ExpenseItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExpenseItem, newItem: ExpenseItem): Boolean {
        return oldItem == newItem
    }

}

