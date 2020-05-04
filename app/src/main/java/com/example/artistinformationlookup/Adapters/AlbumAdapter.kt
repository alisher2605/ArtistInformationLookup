package com.example.artistinformationlookup.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistinformationlookup.Networking.Responses.Album
import com.example.artistinformationlookup.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.artist_album_adapter.view.*


class AlbumAdapter(
    private val albums: ArrayList<Album>,
    private val onClick: (Album) -> Unit)
    : RecyclerView.Adapter<AlbumAdapter.EventsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.artist_album_adapter, parent, false))

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) =
        holder.binding(albums[position], onClick)

    class EventsHolder(private val view: View)
        : RecyclerView.ViewHolder(view){
        fun binding(album: Album, onClick: (Album) -> Unit){
            view.txtName.text = album.albumName
            view.txtYear.text = album.releasedYear.toString()
            view.txtGenre.text = album.genre
            if (!album.thumbnailPath.isNullOrBlank()){
                Picasso.get().load(album.thumbnailPath).into(view.albumThumb)
            }
        }
    }
}