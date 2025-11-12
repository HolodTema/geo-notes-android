package com.terabyte.geonotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terabyte.geonotes.room.GeoNote
import com.terabyte.geonotes.room.RoomManager
import com.terabyte.geonotes.ui.GeoNoteAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _liveDataBottomNavMenuItemId = MutableLiveData<Int>()
    val liveDataBottomNavMenuItemId: LiveData<Int> = _liveDataBottomNavMenuItemId

    private val _liveDataGeoNotes = MutableLiveData<List<GeoNote>>()
    val liveDataGeoNotes: LiveData<List<GeoNote>> = _liveDataGeoNotes

    init {
        loadAllGeoNotes()
    }

    fun setBottomNavMenuItemId(menuItemId: Int) {
        _liveDataBottomNavMenuItemId.value = menuItemId
    }

    private fun loadAllGeoNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val geoNotes = RoomManager.get().getAllGeoNotes()

            withContext(Dispatchers.Main) {
                _liveDataGeoNotes.value = geoNotes
            }
        }
    }
}