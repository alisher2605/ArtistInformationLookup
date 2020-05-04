package com.example.artistinformationlookup.Networking


import com.example.artistinformationlookup.Networking.Responses.Albums
import com.example.artistinformationlookup.Networking.Responses.ArtistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient{
    @GET("search.php")
    fun getArtistByName(
        @Query("s") s: String
    ): Call<ArtistResponse>

    @GET("searchalbum.php")
    fun getAlbumsByArtistName(
        @Query("s") s: String
    ): Call<Albums>

    @GET("searchalbum.php")
    fun getAlbumDetailsByName(
        @Query("a") a: String
    ): Call<Albums>

}
