package com.example.app_api.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Chat::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}