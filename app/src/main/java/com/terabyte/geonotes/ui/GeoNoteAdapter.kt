package com.terabyte.geonotes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.terabyte.geonotes.databinding.ListItemGeonoteBinding
import com.terabyte.geonotes.room.GeoNote
import java.text.SimpleDateFormat
import java.util.Locale

class Holder(
    private val binding: ListItemGeonoteBinding,
    private val itemClickListener: (geoNote: GeoNote) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    fun bind(geoNote: GeoNote) {
        binding.textDate.text = dateFormat.format(geoNote.date)
        binding.textLatitude.text = geoNote.latitude.toString()
        binding.textLongitude.text = geoNote.longitude.toString()
        binding.textGeoNote.text = geoNote.text
        binding.colorIndicator.background.setTint(geoNote.color)

        binding.root.setOnClickListener {
            itemClickListener(geoNote)
        }
    }
}

private class GeoNoteDiffUtilCallback : DiffUtil.ItemCallback<GeoNote>() {

    override fun areContentsTheSame(
        oldItem: GeoNote,
        newItem: GeoNote
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: GeoNote,
        newItem: GeoNote
    ): Boolean {
        return oldItem.id == newItem.id
    }
}

class GeoNoteAdapter(
    private val inflater: LayoutInflater,
    private val itemClickListener: (geoNote: GeoNote) -> Unit
) :
    ListAdapter<GeoNote, Holder>(GeoNoteDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemGeonoteBinding.inflate(inflater, parent, false)
        return Holder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}