package com.android.example.wcmap.model.wc

import com.squareup.moshi.Json

data class WebApiResult(
    @Json(name = "nhits") var nhits: Int,
    @Json(name = "parameters") var parameters: Parameters,
    @Json(name = "records") var records: List<Records>
)