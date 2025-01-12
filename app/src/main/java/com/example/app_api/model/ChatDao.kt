package com.example.app_api.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatDao {
    @Query("SELECT * FROM chat")
    fun getAll(): LiveData<List<Chat>>

    @Insert
    fun insertAll(vararg chats: Chat)

    @Delete
    fun delete(chat: Chat)
}
