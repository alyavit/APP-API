package com.example.app_api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class Chat(
    @PrimaryKey val id: Int,
    val name: String,
    val isFavorite: Boolean
)

data class MessageForRequest(
    val role: String,
    val content: String
)

data class TokenResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
)

data class CompletionRequest(
    val model: String,
    val messages: List<Message>,
    val stream: Boolean,
    val updateInterval: Int
)

data class CompletionResponse(
    val choices: List<Choice>,
    //val created: Long,
    //val model: String,
    //val usage: Usage,
    //val `object`: String
)

data class Choice(
    val message: Message,
    val index: Int,
    //val finish_reason: String
)

data class Message(
    val role: String,
    val content: String,
    //val created: Long,
    //val name: String,
    //val functions_state_id: String,
    //val function_call: FunctionCall,
    //val data_for_context: List<Any>
)
