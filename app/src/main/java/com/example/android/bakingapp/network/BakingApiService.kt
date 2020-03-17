package com.example.android.bakingapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://d17h27t6h515a5.cloudfront.net/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL).build()

interface BakingApiService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    suspend fun getProperties(): ArrayList<Recipe>
}

object BakingApi{
    val retrofitService: BakingApiService by lazy {
        retrofit.create(BakingApiService::class.java)
    }
}