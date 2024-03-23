package com.altafrazzaque.splitify.views.adapter

import com.altafrazzaque.splitify.model.ExpenseItem
import javax.inject.Inject

class ClickListener @Inject constructor() {

    var onItemClick: ((ExpenseItem) -> Unit)? = null

    fun onClick(data: ExpenseItem) {
        onItemClick?.invoke(data)
    }
}