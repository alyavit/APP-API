package com.example.app_api.api

import com.example.app_api.models.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("character/")
    suspend fun getCharacter(@Query("page") page: Int): Response<CharacterList>


}
