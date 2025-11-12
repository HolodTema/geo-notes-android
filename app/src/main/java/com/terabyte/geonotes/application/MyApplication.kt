package com.terabyte.geonotes.application

import android.app.Application
import com.terabyte.geonotes.room.RoomManager

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        RoomManager.init(applicationContext)
    }
}