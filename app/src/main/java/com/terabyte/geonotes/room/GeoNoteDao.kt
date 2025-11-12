package com.terabyte.geonotes.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.UUID

@Dao
interface GeoNoteDao {

    @Query("SELECT * FROM geo_notes")
    fun getAll(): List<GeoNote>

    @Query("SELECT * FROM geo_notes WHERE id = :id")
    fun getById(id: UUID): GeoNote

    @Insert
    fun insert(geoNote: GeoNote)

    @Update
    fun update(geoNote: GeoNote)

    @Delete
    fun delete(geoNote: GeoNote)
}