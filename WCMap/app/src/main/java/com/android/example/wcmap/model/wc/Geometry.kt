package com.android.example.wcmap.model.wc

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geometry(

    @Json(name = "type") var type: String,
    @Json(name = "coordinates") var coordinates: List<Double>

) : Parcelable