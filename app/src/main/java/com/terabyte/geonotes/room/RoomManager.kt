package com.terabyte.geonotes.room

import android.content.Context
import androidx.room.Room
import com.terabyte.geonotes.ROOM_DB_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RoomManager(context: Context) {
    private val database: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, ROOM_DB_NAME)
            .build()

    fun getAllNotes(listener: (List<GeoNote>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) {
                database.geoNoteDao().getAll()
            }
            listener(deferred.await())
        }
    }

    fun createNote(geoNote: GeoNote, listener: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) {
                database.geoNoteDao().insert(geoNote)
            }
            listener()
        }
    }

    fun updateNote(geoNote: GeoNote, listener: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) {
                database.geoNoteDao().update(geoNote)
            }
            listener()
        }
    }

    fun deleteNote(geoNote: GeoNote, listener: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) {
                database.geoNoteDao().delete(geoNote)
            }
            listener()
        }
    }


}