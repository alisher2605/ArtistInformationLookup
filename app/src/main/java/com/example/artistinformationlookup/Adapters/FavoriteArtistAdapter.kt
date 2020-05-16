package com.example.artistinformationlookup.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistinformationlookup.Networking.Responses.ArtistInfoItem
import com.example.artistinformationlookup.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.artist_album_adapter.view.*
import kotlinx.android.synthetic.main.favorite_adapter.view.*


class FavoriteArtistAdapter(
    private val favoriteArtistList: List<ArtistInfoItem>,
    private val onClick: (ArtistInfoItem) -> Unit)
    : RecyclerView.Adapter<FavoriteArtistAdapter.EventsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_adapter, parent, false))

    override fun getItemCount() = favoriteArtistList.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) =
        holder.binding(favoriteArtistList[position], onClick)

    class EventsHolder(private val view: View)
        : RecyclerView.ViewHolder(view){
        fun binding(favoriteArtist: ArtistInfoItem, onClick: (ArtistInfoItem) -> Unit){
            view.txtFavoriteName.text = favoriteArtist.artistName
            view.txtFavoriteYear.text = favoriteArtist.bornYear.toString()
            view.txtFavoriteGenre.text = favoriteArtist.genre.toString()
            if (!favoriteArtist.thumbnailPath.isNullOrBlank()){
                Picasso.get().load(favoriteArtist.thumbnailPath).into(view.albumThumb)
            }
            view.setOnClickListener{
                onClick(favoriteArtist)
            }
        }
    }
}