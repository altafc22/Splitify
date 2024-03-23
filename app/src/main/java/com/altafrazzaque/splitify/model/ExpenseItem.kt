package com.altafrazzaque.splitify.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class ExpenseItem(
    @PrimaryKey
    @Json(name = "id")
    val id : String,
    @Json(name = "item") val item: String,
    @Json(name = "price") val price: Int,
    @Json(name = "paidBy") val paidBy: String,
    @Json(name = "date") val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
        Date()
    )
) : Parcelable

const val TEST_EXPENSE_DATA = """
[
  {
    "id":"5fj72Gp",
    "item": "eggs",
    "price": 43,
    "paidBy": "John",
    "date": "2024-03-23T12:00:00Z"
  },
  {
    "id":"K8lZmN3",
    "item": "pizza",
    "price": 12,
    "paidBy": "Alice",
    "date": "2024-03-22T18:30:00Z"
  },
  {
    "id":"R9qWvS1",
    "item": "salad",
    "price": 8,
    "paidBy": "Bob",
    "date": "2024-03-21T13:45:00Z"
  },
  {
    "id":"y2oU6Ft",
    "item": "steak",
    "price": 25,
    "paidBy": "Emily",
    "date": "2024-03-20T19:15:00Z"
  }
]
"""