package com.example.artistinformationlookup.Networking.Loaders

import com.example.artistinformationlookup.Networking.ApiFactory
import com.example.artistinformationlookup.Networking.Responses.ArtistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistManager(
    private val onSuccess:(ArtistResponse) -> Unit,
    private val onError:(Throwable) -> Unit
) {


    fun getArtistInfo(name: String) {
        ApiFactory.getApiCient()
            .getArtistByName(name).enqueue(object : Callback<ArtistResponse> {
                override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                    onError(t)
                }
                override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                    onSuccess(response.body()!!)
                }
            }
            )
    }
}