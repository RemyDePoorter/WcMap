package com.android.example.wcmap.model.wc

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Parameters(

    @Json(name = "dataset") var dataset: String,
    @Json(name = "rows") var rows: Int,
    @Json(name = "start") var start: Int,
    @Json(name = "format") var format: String,
    @Json(name = "timezone") var timezone: String

):Parcelable