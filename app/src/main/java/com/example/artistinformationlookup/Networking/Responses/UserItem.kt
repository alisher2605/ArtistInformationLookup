package com.example.artistinformationlookup.Networking.Responses

data class UserItem(
    val uid: String,
    val email: String,
    val username: String,
    val favoriteArtists: List<ArtistInfoItem>
){
    constructor():this("","","", emptyList())
}