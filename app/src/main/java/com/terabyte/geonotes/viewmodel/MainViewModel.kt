package com.terabyte.geonotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terabyte.geonotes.R
import com.terabyte.geonotes.application.MyApplication
import com.terabyte.geonotes.room.GeoNote

class MainViewModel(private val application: Application) : AndroidViewModel(application) {
    val liveDataBottomNavChosenId = MutableLiveData<Int>(R.id.menuItemMap)
    val liveDataNotes = MutableLiveData<List<GeoNote>>()

    init {
        (application as MyApplication).roomManager.getAllNotes { notes ->
            liveDataNotes.value = notes
        }
    }

    class Factory(private val application: Application) :
        ViewModelProvider.AndroidViewModelFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }
}