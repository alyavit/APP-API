package com.example.app_api.network

import com.example.app_api.model.Chat
import com.example.app_api.model.CompletionRequest
import com.example.app_api.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

/*interface ChatApiService {
    @GET("chats")
    fun getChats(): Call<List<Chat>>
}

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<User> createUser(@Body User user);
}*/

public interface ChatApiService {
    @GET ("chats")
    fun getChats(): Call<List<Chat>>

    @fun sendMessage(chatId: Int, message: Any)

}

interface GigaChatApiAuth {
    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Accept: application/json",
        "RqUID: f111c17e-3461-457e-91fb-1c95a9941aee", // Уникальный идентификатор запроса
        "Authorization: Basic MGU5ZGMwOWQtNDM1Mi00ZTU0LTg4NDYtNWIwZGZjYjQzNmFmOjNmYmNkZjk3LWJhMjMtNDNiMS04NjY5LWQ2MmIxODc1Mjk2OQ==" // Ваш базовый токен
    )
    @FormUrlEncoded
    @POST("api/v2/oauth")
    fun getToken(@Field("scope") scope: String): Call<TokenResponse>
}

interface GigaChatApiGetResponse {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Authorization: Bearer <TOKEN>" //TODO: Замените <TOKEN> на ваш токен получаемый в запросе GetAccessToken
     )
    @POST("api/v1/chat/completions")
    fun createCompletion(@Body request: CompletionRequest): Call<ApiResponse>
}