package com.example.artistinformationlookup.Activities.Information

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistinformationlookup.Adapters.FavoriteArtistAdapter
import com.example.artistinformationlookup.Networking.Responses.ArtistInfoItem
import com.example.artistinformationlookup.Entities.UserItem
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
        loadFav(auth.currentUser!!.uid)

    }
    private fun loadFav(uid: String){
        database.collection("users")
            .document(uid)
            .addSnapshotListener{snapshot, e->
                if (e != null){
                    Log.d("errrorr", e.localizedMessage)
                    return@addSnapshotListener
                }
                val user = snapshot?.toObject(UserItem::class.java)
                displayFavArtists(user!!.favoriteArtists)


            }
    }
    private fun displayFavArtists(artists: List<ArtistInfoItem>){
        favorite_artist_list.adapter = FavoriteArtistAdapter(
            artistList = artists, onClick = {
               val intent = Intent(this, ArtistInfoActivity::class.java)
                intent.putExtra("ARTISTNAME", it.artistName)
                startActivity(intent)
            })
        favorite_artist_list.layoutManager = LinearLayoutManager(this)
    }


}
