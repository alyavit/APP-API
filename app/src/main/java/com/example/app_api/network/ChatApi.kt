package com.example.app_api.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChatApi {
    private const val BASE_URL = "https://gigachat.devices.sberbank.ru/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val chatApiService: ChatApiService = retrofit.create(ChatApiService::class.java)


}

object ChatApiAuth{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://ngw.devices.sberbank.ru:9443/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi = retrofit.create(GigaChatApiAuth::class.java)
}