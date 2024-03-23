package com.altafrazzaque.splitify.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SpentBy(
    @Json(name = "name")
    val name:String,
    @Json(name = "amount")
    val amount: String
) : Parcelable