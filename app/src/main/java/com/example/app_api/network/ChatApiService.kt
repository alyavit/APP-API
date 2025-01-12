package com.example.app_api.network

import com.example.app_api.model.Chat
import retrofit2.Call
import retrofit2.http.GET

interface ChatApiService {
    @GET("chats")
    fun getChats(): Call<List<Chat>>
}