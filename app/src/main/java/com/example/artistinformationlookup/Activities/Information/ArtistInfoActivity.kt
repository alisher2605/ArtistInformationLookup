package com.example.artistinformationlookup.Activities.Information

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.artistinformationlookup.Activities.Authorization.LoginActivity
import com.example.artistinformationlookup.Networking.Loaders.ArtistManager
import com.example.artistinformationlookup.Networking.Responses.ArtistInfoItem
import com.example.artistinformationlookup.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_artist_info.*

class ArtistInfoActivity : AppCompatActivity() {
    private  val database by lazy{ FirebaseFirestore.getInstance()}
    private  val auth by lazy{ FirebaseAuth.getInstance()}

    private var artistId = 0
    companion object{
        const val ARTISTNAME = "ARTISTNAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_info)
        val artistName = intent.getStringExtra(ARTISTNAME)
        Toast.makeText(this, artistName, Toast.LENGTH_LONG).show()
        loadArtistInfo(artistName)
        addToFavorites(artistName)
        title = "Artist"
    }



    private fun loadArtistInfo(name: String){
        ArtistManager(
            onSuccess = {
                val artists = it.favoriteArtists
                Log.d("taag", artists.toString())
                if (!artists.isNullOrEmpty()) {
                    val artist = artists[0]
                    artistId = artist.id
                    Log.d("taag", artist.toString())
                    artistName.text = artist.artistName
                    artistBio.text = artist.biography
                    artistYear.text = artist.bornYear.toString()
                    artistGenre.text = artist.genre
                    artistWebsite.text = artist.website
                    if (!artist.thumbnailPath.isNullOrBlank()) {
                        Picasso.get().load(artist.thumbnailPath).into(arthumb)
                    }
                    if (!artist.logoPath.isNullOrBlank()) {
                        Picasso.get().load(artist.logoPath).into(artistLogo)
                    }
                }

            },
            onError = {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        ).getArtistInfo(name)
    }


    private fun addToFavorites(name: String){
        ArtistManager(
            onSuccess = {
                val artists = it.favoriteArtists
                if (!artists.isNullOrEmpty()) {
                    btn_add_to_favorite.setOnCheckedChangeListener { buttonView, isChecked ->
                        buttonView.setOnClickListener {
                        if (auth.currentUser != null) {
                            val artist = artists[0]
                            val artistInfo = ArtistInfoItem(
                                auth.currentUser!!.uid,
                                artist.id,
                                artist.artistName,
                                artist.bornYear,
                                artist.genre,
                                artist.website,
                                artist.biography,
                                artist.logoPath,
                                artist.thumbnailPath
                            )
                            if (isChecked) {
                                insertFavoriteInDatabase(artistInfo)
                                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                            }
                            else{
                                removeFavoriteInDatabase(artistInfo)
                                Toast.makeText(this, "Removed", Toast.LENGTH_LONG).show()
                            }
                        }
                        else {
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }

            },
            onError = {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        ).getArtistInfo(name)
    }

    private fun insertFavoriteInDatabase(
        favoriteArtistInfo:ArtistInfoItem
    ){
        database.collection("users")
            .document(auth!!.currentUser!!.uid)
            .update("artists", FieldValue.arrayUnion(favoriteArtistInfo)).addOnSuccessListener {
                Log.d("taaag","Works!")
            }
            .addOnFailureListener {
                Log.d("taaag", it.localizedMessage)
            }
    }

    private fun removeFavoriteInDatabase(
        favoriteArtistInfo:ArtistInfoItem
    ){
        database.collection("users")
            .document(auth!!.currentUser!!.uid)
            .update("artists", FieldValue.arrayRemove(favoriteArtistInfo))
            .addOnSuccessListener {
                Log.d("taaag","Works!")
            }
            .addOnFailureListener {
                Log.d("taaag", it.localizedMessage)
            }
    }
}
