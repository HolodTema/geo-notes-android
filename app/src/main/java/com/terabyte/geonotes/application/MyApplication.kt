package com.terabyte.geonotes.application

import android.app.Application
import com.terabyte.geonotes.room.RoomManager

class MyApplication: Application() {
    lateinit var roomManager: RoomManager

    override fun onCreate() {
        super.onCreate()
        roomManager = RoomManager(this)
    }
}