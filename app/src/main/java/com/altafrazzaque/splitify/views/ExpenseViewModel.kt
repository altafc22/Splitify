package com.altafrazzaque.splitify.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altafrazzaque.splitify.model.ExpenseItem
import com.altafrazzaque.splitify.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    fun getExpenses() = expenseRepository.expenses

    fun getTotalThisMonth() = expenseRepository.getTotalThisMonth

    fun refreshUserDetails() = viewModelScope.launch(Dispatchers.IO) {
        expenseRepository.refreshHistoryList()
    }

    fun addExpense(item: ExpenseItem) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.addExpense(item)
        }
    }

}