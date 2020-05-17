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
    private val artistList: List<ArtistInfoItem>,
    private val onClick: (ArtistInfoItem) -> Unit)
    : RecyclerView.Adapter<FavoriteArtistAdapter.EventsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_adapter, parent, false))

    override fun getItemCount() = artistList.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) =
        holder.binding(artistList[position], onClick)

    class EventsHolder(private val view: View)
        : RecyclerView.ViewHolder(view){
        fun binding(artistList: ArtistInfoItem, onClick: (ArtistInfoItem) -> Unit){
            view.txtFavoriteName.text = artistList.artistName
            view.txtFavoriteYear.text = artistList.bornYear.toString()
            view.txtFavoriteGenre.text = artistList.genre.toString()
            if (!artistList.thumbnailPath.isNullOrBlank()){
                Picasso.get().load(artistList.thumbnailPath).into(view.FavoriteThumb)
            }

            view.setOnClickListener{
                onClick(artistList)
            }
        }
    }
}