package com.example.artistinformationlookup.Networking


import com.example.artistinformationlookup.Networking.Repositories.ArtistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient{
    @GET("search.php")
    fun getArtistByName(
        @Query("s") s: String
    ): Call<ArtistResponse>
}
