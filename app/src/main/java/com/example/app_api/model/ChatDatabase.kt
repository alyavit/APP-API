package com.example.app_api.model

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Chat::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao

    companion object {
        fun getDatabase(application: Application, viewModelScope: CoroutineScope): ChatDatabase {
            val database = Room.databaseBuilder(
                application,
                ChatDatabase::class.java,
                "chat_database"
            ).build()

            return database
        }
    }
}



