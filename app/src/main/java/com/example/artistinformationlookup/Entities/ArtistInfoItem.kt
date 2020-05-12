package com.example.artistinformationlookup.Entities

data class ArtistInfoItem (
    val userId:String,
    val artistId:String,
    val artistName:String,
    val artistBornYear:String,
    val artistGenre:String,
    val thumbUrl:String
){
    constructor(): this("","", "", "", "", "")
}