package com.altafrazzaque.splitify.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.altafrazzaque.splitify.database.AppDatabase
import com.altafrazzaque.splitify.network.ExpenseService
import com.altafrazzaque.splitify.model.ExpenseItem
import com.altafrazzaque.splitify.model.TEST_EXPENSE_DATA
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseService: ExpenseService,
    private val database: AppDatabase
) {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val expenses: LiveData<List<ExpenseItem>?> = database.expenseDao.getExpenses().map { it }

    val getTotalThisMonth : LiveData<Double?> = database.expenseDao.getTotalThisMonth()

    suspend fun refreshHistoryList() {
        try {
            //val items = historyListService.getHistoryList() // data from api
            val jsonString = TEST_EXPENSE_DATA
            val jsonAdapter = moshi.adapter<List<ExpenseItem>>(
                Types.newParameterizedType(List::class.java, ExpenseItem::class.java)
            )
            val items = jsonAdapter.fromJson(jsonString) ?: throw IllegalStateException("Invalid JSON")
            database.expenseDao.insertAll(items)
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

    fun addExpense(item: ExpenseItem){
        try {
           database.expenseDao.insert(item)
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}