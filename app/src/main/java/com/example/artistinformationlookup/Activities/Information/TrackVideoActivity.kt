package com.example.artistinformationlookup.Activities.Information

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.artistinformationlookup.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_track_video.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TrackVideoActivity : AppCompatActivity() {
    companion object{
        const val TRACKNAME = "TRACKNAME"
        const val THUMBPATH = "THUMBPATH"
        const val VIDEOLINK = "VIDEOLINK"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_video)
        loadSong()
    }

    private fun loadSong(){
        val trackName = intent.getStringExtra(TRACKNAME)
        val thumbnailPath = intent.getStringExtra(THUMBPATH)
        val videoLink = intent.getStringExtra(VIDEOLINK)
        Picasso.get().load(thumbnailPath).into(img_song_thumb)
        txt_song.text = trackName
        if (videoLink!=null){
            openYoutubeLink(videoLink)
        }
        else{
            Toast.makeText(this, "This song doesn't have a clip or it is absent in the database :(" , Toast.LENGTH_LONG).show()

        }

    }

    private fun openYoutubeLink(youtubeID: String) {
      btnOpenTrackInBrowser.setOnClickListener {
          val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeID))
          val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeID))
          try {
              this.startActivity(intentApp)
          } catch (ex: ActivityNotFoundException) {
              this.startActivity(intentBrowser)
          }

      }
    }
}
