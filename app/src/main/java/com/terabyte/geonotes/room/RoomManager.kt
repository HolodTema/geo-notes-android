package com.terabyte.geonotes.room

import android.content.Context
import androidx.room.Room
import com.terabyte.geonotes.ROOM_DB_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RoomManager(context: Context) {
    private val db: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, ROOM_DB_NAME)
            .build()

    companion object {
        private lateinit var instance: RoomManager

        fun init(context: Context) {
            instance = RoomManager(context)
        }

        fun get(): RoomManager {
            return instance
        }
    }
}