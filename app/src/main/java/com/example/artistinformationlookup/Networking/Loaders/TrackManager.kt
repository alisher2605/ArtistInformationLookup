package com.example.artistinformationlookup.Networking.Loaders

import com.example.artistinformationlookup.Networking.ApiFactory
import com.example.artistinformationlookup.Networking.Responses.Tracks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrackManager(
    private val onSuccess:(Tracks) -> Unit,
    private val onError:(Throwable) -> Unit
){
    fun getAlbumTracks(id: Int){
        ApiFactory.getApiCient()
            .getAlbumTracksById(id).enqueue(object : Callback<Tracks> {
                override fun onFailure(call: Call<Tracks>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Tracks>, response: Response<Tracks>) {
                    onSuccess(response.body()!!)
                }
            }
            )
    }
}