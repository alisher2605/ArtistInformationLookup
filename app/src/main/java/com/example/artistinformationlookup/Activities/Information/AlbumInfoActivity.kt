package com.example.artistinformationlookup.Activities.Information

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.artistinformationlookup.Networking.Loaders.AlbumManager
import com.example.artistinformationlookup.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_info.*

class AlbumInfoActivity : AppCompatActivity() {
    companion object{
        const val ALBUMNAME = "ALBUMNAME"
        const val ARTISTNAME = "ARTISTNAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_info)
        val albumName = intent.getStringExtra(ALBUMNAME)
        val artistName = intent.getStringExtra(ARTISTNAME)
        loadDetails(albumName, artistName)
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
}
