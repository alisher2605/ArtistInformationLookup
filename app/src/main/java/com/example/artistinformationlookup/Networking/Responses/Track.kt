package com.example.artistinformationlookup.Networking.Responses

import com.google.gson.annotations.SerializedName
data class Track (
    @SerializedName("strTrack")
    val trackName: String,
    @SerializedName("strAlbum")
    val albumName: String,
    @SerializedName("intDuration")
    val duration: Int?,
    @SerializedName("strGenre")
    val genre: String?,
    @SerializedName("strMood")
    val mood: String?,
    @SerializedName("strTrackThumb")
    val thumbnailPath: String?,
    @SerializedName("strMusicVid")
    val musicVideo: String?
)

data class Tracks (
    @SerializedName("track")
    val track : List<Track>
)
