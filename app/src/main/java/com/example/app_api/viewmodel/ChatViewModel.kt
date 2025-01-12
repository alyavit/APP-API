package com.example.app_api.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.app_api.model.Chat
import com.example.app_api.model.ChatDatabase
import com.example.app_api.network.ChatApi
import com.example.app_api.repository.ChatRepository
//import kotlinx.coroutines.Dispatchers

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ChatRepository
    val allChats: LiveData<List<Chat>>

    init {
        val chatDao = ChatDatabase.getDatabase(application, viewModelScope).chatDao()
        val chatApiService = ChatApi.chatApiService
        repository = ChatRepository(chatDao, chatApiService)
        allChats = repository.allChats
    }

    fun insert(chat: Chat) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(chat)
    }

    fun delete(chat: Chat) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(chat)
    }

    fun getChatsFromApi() = viewModelScope.launch(Dispatchers.IO) {
        repository.getChatsFromApi()
    }
}