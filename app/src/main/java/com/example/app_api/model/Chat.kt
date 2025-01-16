package com.example.app_api.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String, // Название чата
    val timestamp: Long // Время создания чата
)

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val chatId: Long, // ID чата, к которому относится сообщение
    val sender: String, // "user" или "gpt"
    val message: String, // Текст сообщения
    val timestamp: Long // Время отправки сообщения
)


//Для распознавания Токена
data class TokenResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
)



//Каждое отдельное сообщение для отправки, где role межет быть "user" (сообщение пользователя) или "assistant" (ответ ассистента), content - текст сообщения
data class MessageForRequest(
    val role: String,
    val content: String
)
//Класс для отправки списка сообщений в формате JSON
data class CompletionRequest(
    val model: String,
    val messages: List<MessageForRequest>,
    val stream: Boolean,
    val updateInterval: Int
)

//Распознаваемое объект JSON при получении ответа на запрос
data class CompletionResponse(
    val choices: List<Choice>,
    //val created: Long,
    //val model: String,
    //val usage: Usage,
    //val `object`: String
)

//Поле объекта CompletionResponse с целью достать сообщение из Message
data class Choice(
    val message: Message,
    val index: Int,
    //val finish_reason: String
)

//Распознаваемое сообщение при получении ответа на запрос
data class Message(
    val role: String,
    val content: String,
    //val created: Long,
    //val name: String,
    //val functions_state_id: String,
    //val function_call: FunctionCall,
    //val data_for_context: List<Any>
)
