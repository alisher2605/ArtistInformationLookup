package com.example.artistinformationlookup.Activities.Information

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistinformationlookup.Adapters.TrackAdapter
import com.example.artistinformationlookup.Networking.Loaders.AlbumManager
import com.example.artistinformationlookup.Networking.Loaders.TrackManager
import com.example.artistinformationlookup.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_info.*

class AlbumInfoActivity : AppCompatActivity() {
    companion object{
        const val ALBUMNAME = "ALBUMNAME"
        const val ARTISTNAME = "ARTISTNAME"
        const val ALBUMID = "ALBUMID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_info)
        val albumName = intent.getStringExtra(ALBUMNAME)
        val artistName = intent.getStringExtra(ARTISTNAME)
        val albumId = intent.getIntExtra(ALBUMID, 0)
        loadDetails(albumName, artistName)
        loadTracks(albumId)
    }

    private fun loadDetails(name: String, artist: String) = AlbumManager(
        onSuccess = {
            val albums = it.album
            if (albums.isNullOrEmpty()){
                Toast.makeText(this, "This album doesn't exist", Toast.LENGTH_LONG).show()
            }
            for(album in albums){
                if (album.artist == artist){
                    albumName.text = album.albumName
                    albumDescription.text = album.description
                    albumArtist.text = album.artist
                    if (!album.thumbnailPath.isNullOrBlank()) {
                        Picasso.get().load(album.thumbnailPath).into(albumThumb)
                    }
                }

            }
        },
        onError = {
            Toast.makeText(this, it.message , Toast.LENGTH_LONG).show()
        }
    ).getAlbumsByAlbumName(name)

    private fun loadTracks(id: Int) = TrackManager(
        onSuccess = {
            val tracks = it.track
            if (tracks.isNullOrEmpty()) {
                Toast.makeText(this, "Doesn't exist", Toast.LENGTH_LONG).show()
                return@TrackManager
            }
            list_album_tracks.adapter = TrackAdapter(ArrayList(tracks), onClick = {
            })
            list_album_tracks.layoutManager = LinearLayoutManager(this)
        },
        onError = {
            Toast.makeText(this, it.message , Toast.LENGTH_LONG).show()
        }).getAlbumTracks(id)
}
