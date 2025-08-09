package com.terabyte.geonotes.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.terabyte.geonotes.ROOM_DB_VERSION

@Database(entities = [GeoNote::class], version = ROOM_DB_VERSION)
abstract class AppDatabase: RoomDatabase() {
    abstract fun geoNoteDao(): GeoNoteDao
}