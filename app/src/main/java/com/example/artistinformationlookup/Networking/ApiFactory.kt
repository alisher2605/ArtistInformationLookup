package com.example.artistinformationlookup.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://theaudiodb.com/api/v1/json/1/"

    fun getRerofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiCient() =
        getRerofit().create(ApiClient::class.java)

}