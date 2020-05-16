package com.example.artistinformationlookup.Activities.Information

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistinformationlookup.Adapters.FavoriteArtistAdapter
import com.example.artistinformationlookup.Networking.Responses.ArtistInfoItem
import com.example.artistinformationlookup.Networking.Responses.UserItem
import com.example.artistinformationlookup.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_favorites_artists.*

class FavoritesArtistsActivity : AppCompatActivity() {
    private val database by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_artists)
        showFavorite(auth.currentUser!!.uid)

    }

    private fun showFavorite(uid: String) {
        database.collection("users")
            .document(uid)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.d("taaag", e.localizedMessage)
                    return@addSnapshotListener
                }
                val user = snapshot?.toObject(UserItem::class.java)
                if (user != null){
                    displayFavArtists(user.favoriteArtists)
                    Toast.makeText(this, user.favoriteArtists.toString(), Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "DOESN'T WORK", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun displayFavArtists(artists: List<ArtistInfoItem>){
        favorite_artist_list.adapter = FavoriteArtistAdapter(
            favoriteArtistList = artists, onClick = {
                Toast.makeText(this,"sdasdasd", Toast.LENGTH_LONG).show()
            })
        favorite_artist_list.layoutManager = LinearLayoutManager(this)
    }


}
