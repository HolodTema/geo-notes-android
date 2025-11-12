package com.terabyte.geonotes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.terabyte.geonotes.databinding.ListItemGeonoteBinding
import com.terabyte.geonotes.room.GeoNote

class RecyclerNotesAdapter(private val notes: List<GeoNote>): RecyclerView.Adapter<RecyclerNotesAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemGeonoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    class Holder(private val binding: ListItemGeonoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: GeoNote) {
            binding.textNote.text = note.text
            binding.textNoteDate.text = note.date
            binding.textNoteLatitude.text = note.latitude.toString()
            binding.textNoteLongitude.text = note.longitude.toString()
        }
    }
}