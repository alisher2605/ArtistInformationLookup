package com.example.artistinformationlookup.Networking

import com.example.artistinformationlookup.Networking.Repositories.ArtistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistLoader(
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