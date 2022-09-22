package com.android.example.wcmap.model.wc

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Records(

    @Json(name = "datasetid") var datasetid: String,
    @Json(name = "recordid") var recordid: String,
    @Json(name = "fields") var fields: Fields,
    @Json(name = "geometry") var geometry: Geometry,
    @Json(name = "record_timestamp") var recordTimestamp: String

):Parcelable