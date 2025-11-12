package com.terabyte.geonotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terabyte.geonotes.room.GeoNote
import com.terabyte.geonotes.room.RoomManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GeoNoteDetailsViewModel : ViewModel() {
    lateinit var geoNote: GeoNote
    lateinit var activityMode: ActivityMode

    fun isInitialized(): Boolean {
        return ::geoNote.isInitialized && ::activityMode.isInitialized
    }

    fun updateGeoNote() {
        viewModelScope.launch(Dispatchers.IO) {
            RoomManager.get().updateGeoNote(geoNote)
        }
    }

    fun insertGeoNote() {
        geoNote = GeoNote.getDefault()
        viewModelScope.launch(Dispatchers.IO) {
            RoomManager.get().insertGeoNote(geoNote)
        }
    }

    fun deleteGeoNote(listener: ()->Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            RoomManager.get().deleteGeoNote(geoNote)
            withContext(Dispatchers.Main) {
                listener()
            }
        }
    }

    enum class ActivityMode {
        ACTIVITY_MODE_INSERT_NOTE,
        ACTIVITY_MODE_UPDATE_NOTE
    }
}