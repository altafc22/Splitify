package com.altafrazzaque.splitify.network

import com.altafrazzaque.splitify.model.ExpenseItem
import retrofit2.http.GET

interface ExpenseService {
    @GET("ENDPOINT_TO_GET_RESULT_FROM_API")
    suspend fun getHistoryList(): List<ExpenseItem>
}
