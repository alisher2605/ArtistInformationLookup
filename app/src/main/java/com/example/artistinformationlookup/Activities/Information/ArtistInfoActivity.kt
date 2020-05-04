package com.example.artistinformationlookup.Activities.Information

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.artistinformationlookup.Networking.ArtistLoader
import com.example.artistinformationlookup.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_artist_info.*

class ArtistInfoActivity : AppCompatActivity() {
    private var artistId = 0
    companion object{
        const val ARTISTNAME = "ARTISTNAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_info)
        val artistName = intent.getStringExtra(ARTISTNAME)
        Toast.makeText(this, artistName, Toast.LENGTH_LONG).show()
        //Забавно, если передать значение null и зайти в инфу об артистах,
        // то он найдет группу каких-то ноунеймов по дефолтному значению textview  lorem ipsum
        loadArtistInfo(artistName)

    }



    private fun loadArtistInfo(name: String){
        ArtistLoader(
            onSuccess = {
                val artists = it.artists
                Log.d("taag", artists.toString())
                if(!artists.isNullOrEmpty()){
                    val artist = artists.get(0)
                    artistId = artist.id
                    Log.d("taag", artist.toString())
                    arName.text = artist.artistName
                    arBio.text = artist.biography
                    arYear.text = artist.bornYear.toString()
                    arGenre.text = artist.genre
                    arWebsite.text = artist.website
                    if (!artist.thumbnailPath.isNullOrBlank()) {
                        Picasso.get().load(artist.thumbnailPath).into(arthumb)
                    }
                    if (!artist.logoPath.isNullOrBlank()) {
                        Picasso.get().load(artist.logoPath).into(arLogo)
                    }
                }
            },
            onError = {
                Toast.makeText(this, it.message , Toast.LENGTH_LONG).show()
            }

        ).getArtistInfo(name)
    }

}
