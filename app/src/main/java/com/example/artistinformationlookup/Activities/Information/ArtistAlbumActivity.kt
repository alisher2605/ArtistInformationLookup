package com.example.artistinformationlookup.Activities.Information

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistinformationlookup.Adapters.AlbumAdapter
import com.example.artistinformationlookup.Networking.Loaders.AlbumManager
import com.example.artistinformationlookup.R
import kotlinx.android.synthetic.main.activity_artist_album.*

class ArtistAlbumActivity : AppCompatActivity() {
    companion object{
        const val ARTISTNAME = "ARTISTNAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_album)

        val artistName = intent.getStringExtra(ARTISTNAME)
        Toast.makeText(this, artistName, Toast.LENGTH_LONG).show()
        loadArtistAlbum(artistName)
        album_author_name.text = "$artistName's albums"
    }

    private fun loadArtistAlbum(name: String){
        AlbumManager(
            onSuccess = {
                val albums = it.album
                list_albums.adapter = AlbumAdapter(ArrayList(albums), onClick = {
                      album ->
                    val intent = Intent(this, AlbumInfoActivity::class.java)
                    intent.putExtra("ALBUMNAME", album.albumName)
                    intent.putExtra("ARTISTNAME", album.artist)
                    intent.putExtra("ALBUMID", album.id)
                    startActivity(intent)
                    Log.d("taaag", "Works")
                })
                list_albums.layoutManager = LinearLayoutManager(this)
            },
            onError = {
                Toast.makeText(this, it.message , Toast.LENGTH_LONG).show()
            }
        ).getAlbumsByArtistName(name)

    }
}
