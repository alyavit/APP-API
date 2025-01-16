package com.example.app_api.network

import com.example.app_api.model.Chat
import com.example.app_api.model.CompletionRequest
import com.example.app_api.model.CompletionResponse
import com.example.app_api.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface GigaChatApiAuth {
    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Accept: application/json",
        "RqUID: f111c17e-3461-457e-91fb-1c95a9941aee", // Уникальный идентификатор запроса, на данный момент он единый для всех запросов
        "Authorization: Basic MGU5ZGMwOWQtNDM1Mi00ZTU0LTg4NDYtNWIwZGZjYjQzNmFmOjNmYmNkZjk3LWJhMjMtNDNiMS04NjY5LWQ2MmIxODc1Mjk2OQ==" // Наш базовый Ключ Авторизации
    )
    @FormUrlEncoded
    @POST("api/v2/oauth")
    fun getToken(@Field("scope") scope: String): Call<TokenResponse> //получение токена
}

interface GigaChatApiGetResponse {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Authorization: Bearer <TOKEN>" //TODO: Замените <TOKEN> на ваш токен получаемый в запросе GetAccessToken
     )
    @@POST("api/v1/chat/completions")
    suspend fun createCompletion(
        @Header("Authorization") authorization: String,
        @Body request: CompletionRequest
    ): CompletionResponse

}