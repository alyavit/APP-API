package com.example.app_api.viewmodel

import ChatRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.app_api.network.ChatApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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


    fun sendMessage(chatId: Long, userMessage: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.sendMessage(chatId, userMessage)
    }}