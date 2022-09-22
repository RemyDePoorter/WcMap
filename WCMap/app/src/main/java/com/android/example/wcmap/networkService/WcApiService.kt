package com.android.example.wcmap.networkService

import com.android.example.wcmap.model.wc.WebApiResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://opendata.bruxelles.be/api/records/1.0/search/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WcApiService {
    @GET("?dataset=bruxelles_toilettes_publiques")
    suspend fun getWC(): WebApiResult
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object WcApi {
    val retrofitService: WcApiService by lazy { retrofit.create(WcApiService::class.java) }
}



