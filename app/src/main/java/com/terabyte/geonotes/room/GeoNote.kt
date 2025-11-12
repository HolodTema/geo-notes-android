package com.terabyte.geonotes.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "geo_notes")
data class GeoNote(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "note_text") val text: String,
    @ColumnInfo(name = "date") val date: Date = Date(),
    @ColumnInfo(name = "color") val color: Int
)
