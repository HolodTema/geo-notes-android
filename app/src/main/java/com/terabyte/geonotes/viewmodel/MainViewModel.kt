package com.terabyte.geonotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terabyte.geonotes.R

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val liveDataBottomNavChosenId = MutableLiveData<Int>(R.id.menuItemMap)

    class Factory(private val application: Application) :
        ViewModelProvider.AndroidViewModelFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }
}