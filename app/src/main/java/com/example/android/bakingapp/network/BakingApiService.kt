package com.example.android.bakingapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL).build()

interface BakingApiService {
    @GET("baking.json")
    fun getProperties(): Deferred<List<BakingProperty>>
}

object BakingApi{
    val retrofitService: BakingApiService by lazy {
        retrofit.create(BakingApiService::class.java)
    }
}