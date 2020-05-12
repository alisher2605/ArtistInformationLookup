package com.example.artistinformationlookup.Activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistinformationlookup.Activities.Authorization.LoginActivity
import com.example.artistinformationlookup.Activities.Information.ArtistAlbumActivity
import com.example.artistinformationlookup.Activities.Information.ArtistInfoActivity
import com.example.artistinformationlookup.Activities.Information.FavoritesArtistsActivity
import com.example.artistinformationlookup.Adapters.CategoryAdapter
import com.example.artistinformationlookup.Entities.CategoryItem
import com.example.artistinformationlookup.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private  val auth by lazy{ FirebaseAuth.getInstance()}

    companion object{
        const val artistInfoImageUrl = "https://outstyle.org/images/news/2/9/0/post-malone.jpg"
        const val artistAlbumInfoImageUrl = "https://www.theaudiodb.com/images/media/album/thumb/uvtwrq1481288068.jpg"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showCategories()
        title = "Main menu"
        /*val btn = findViewById(R.id.imageButton1) as ImageButton
        btn.setImageResource(R.drawable.actions_record)*/
    }

    //=============================
    // Showing menu of categories
    //=============================
    private fun showCategories(){
        category_list.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            CategoryItem(
                imageUrl = artistInfoImageUrl,
                categoryName = "Artist Info"
            ),
            CategoryItem(
                imageUrl = artistAlbumInfoImageUrl,
                categoryName = "Artist's Albums"
            )

        )

        category_list.adapter = CategoryAdapter(items = items, onItemClick = {
            if (it.categoryName ==  "Artist Info"){
                //Забавно, если передать значение null и зайти в инфу об артистах,
                // то он найдет группу каких-то ноунеймов по дефолтному значению textview  lorem ipsum
                val intent = Intent(this, ArtistInfoActivity::class.java)
                    val artistText = textFindArtist.text.toString().trim() //для того что бы убрать нежелательные пробелы
                    if (textFindArtist.text.isEmpty()){
                        Toast.makeText(this, "Please input artist's name", Toast.LENGTH_LONG).show()
                    }
                else{
                        intent.putExtra("ARTISTNAME", artistText)
                        startActivity(intent)
                        // Возвращаться с той страницы когда это закрывается очень не удобно
                        // Так как восле нажатия на категорию других активити нет то я решил убрать finish()
//                      finish()
                    }
            }
            if (it.categoryName == "Artist's Albums"){
                val intent = Intent(this, ArtistAlbumActivity::class.java)
                val artistText = textFindArtist.text.toString().trim() //для того что бы убрать нежелательные пробелы
                if (textFindArtist.text.isEmpty()){
                    Toast.makeText(this, "Please input artist's name", Toast.LENGTH_LONG).show()
                }
                else{
                    intent.putExtra("ARTISTNAME", artistText)
                    startActivity(intent)
                    // Возвращаться с той страницы когда это закрывается очень не удобно
                        // Так как восле нажатия на категорию других активити нет то я решил убрать finish()
//                   finish()
                }
            }


        })
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favoritemenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.menuFavorite){
            if (auth.currentUser == null){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, FavoritesArtistsActivity::class.java)
                startActivity(intent)
                finish()
            }
            return true
        }

        if (id == R.id.logOut){
            if (auth.currentUser == null){
                Toast.makeText(this, "You must sign in first", Toast.LENGTH_LONG).show()
            }
            else{
                auth.signOut()
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
