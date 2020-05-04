package com.example.artistinformationlookup.Networking.Loaders

import com.example.artistinformationlookup.Networking.ApiFactory
import com.example.artistinformationlookup.Networking.Responses.Albums
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumManager (
    private val onSuccess:(Albums) -> Unit,
    private val onError:(Throwable) -> Unit
){
 fun getAlbumsByArtistName(name: String){
     ApiFactory.getApiCient()
         .getAlbumsByArtistName(name).enqueue(object : Callback<Albums> {
             override fun onFailure(call: Call<Albums>, t: Throwable) {
                 onError(t)
             }
             override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                 onSuccess(response.body()!!)
             }
         }
         )
 }


}