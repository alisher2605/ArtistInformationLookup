package com.example.artistinformationlookup.Entities

import com.example.artistinformationlookup.Networking.Responses.ArtistInfoItem

data class UserItem(
    val uid: String,
    val email: String,
    val username: String,
    val favoriteArtists: List<ArtistInfoItem>
)
{
    constructor():this("","","", emptyList())
}
