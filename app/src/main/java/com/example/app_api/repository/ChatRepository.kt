package com.example.app_api.repository

//import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveData
import com.example.app_api.model.Chat
import com.example.app_api.model.ChatDao
import com.example.app_api.network.ChatApiService

class ChatRepository(private val chatDao: ChatDao, private val chatApiService: ChatApiService) {
    //val allChats: LiveData<List<Chat>> = chatDao.getAll()
    val allChats: LiveData<List<Chat>> = chatDao.getAll().asLiveData()


    suspend fun insert(chat: Chat) {
        chatDao.insertAll(chat)
    }

    suspend fun delete(chat: Chat) {
        chatDao.delete(chat)
    }

    suspend fun getChatsFromApi() {
        val response = chatApiService.getChats().execute()
        if (response.isSuccessful) {
            response.body()?.let { chats ->
                chatDao.insertAll(*chats.toTypedArray())
            }
        }
    }
}