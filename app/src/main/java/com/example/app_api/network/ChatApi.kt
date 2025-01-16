package com.example.app_api.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Основной адрес для отправки сообщений и получения ответов
object ChatApi {
    private const val BASE_URL = "https://gigachat.devices.sberbank.ru/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val chatApiService: GigaChatApiGetResponse = retrofit.create(GigaChatApiGetResponse::class.java)


}

//Используется для авторизации и получения токена
object ChatApiAuth{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://ngw.devices.sberbank.ru:9443/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi = retrofit.create(GigaChatApiAuth::class.java)
}