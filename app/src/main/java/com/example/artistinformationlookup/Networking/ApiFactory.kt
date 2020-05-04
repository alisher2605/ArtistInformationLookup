package com.example.artistinformationlookup.Networking

import com.example.artistinformationlookup.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val artistInfoEndpoint = "https://theaudiodb.com/"

    fun getRerofit() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiCient() =
        getRerofit().create(ApiClient::class.java)

}