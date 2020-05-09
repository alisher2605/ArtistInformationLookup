package com.example.artistinformationlookup.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistinformationlookup.Networking.Responses.Track
import com.example.artistinformationlookup.R
import kotlinx.android.synthetic.main.song_adapter.view.*



class TrackAdapter(
    private val tracks: ArrayList<Track>,
    private val onClick: (Track) -> Unit)
    : RecyclerView.Adapter<TrackAdapter.EventsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.song_adapter, parent, false))

    override fun getItemCount() = tracks.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) =
        holder.binding(tracks[position], onClick)

    class EventsHolder(private val view: View)
        : RecyclerView.ViewHolder(view){
        @SuppressLint("SetTextI18n")
        fun binding(track: Track, onClick: (Track) -> Unit){
            view.txt_song_name.text = track.trackName
            val trackDuration = track.duration!!.toInt()
            val minutes = ((trackDuration / 6000)% 60000)/10
            val seconds = trackDuration % 60000 / 1000;
            if (seconds<10){
                view.txt_song_duration.text = "$minutes:0$seconds"
            }
            else{
                view.txt_song_duration.text = "$minutes:$seconds"
            }
            view.setOnClickListener{
                onClick(track)
            }
        }
    }
}