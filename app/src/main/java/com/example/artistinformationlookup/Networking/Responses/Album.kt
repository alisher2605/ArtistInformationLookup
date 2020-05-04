package com.example.artistinformationlookup.Networking.Responses

import com.google.gson.annotations.SerializedName

data class Album (
    @SerializedName("idAlbum")
    val id: Int,
    @SerializedName("strAlbum")
    val albumName: String,
    @SerializedName("intYearReleased")
    val releasedYear: Int,
    @SerializedName("strGenre")
    val genre: String,
    @SerializedName("strDescriptionEN")
    val description: String,
    @SerializedName("strArtist")
    val artist: String,
    @SerializedName("strAlbumThumb")
    val thumbnailPath: String
)
data class Albums (
    @SerializedName("album")
    val album : List<Album>
)