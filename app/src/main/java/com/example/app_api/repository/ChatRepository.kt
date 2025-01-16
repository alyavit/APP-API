import androidx.lifecycle.LiveData
import com.example.app_api.model.ChatDao
import com.example.app_api.model.CompletionRequest
import com.example.app_api.model.MessageDao
import com.example.app_api.model.MessageEntity
import com.example.app_api.model.MessageForRequest
import com.example.app_api.model.TokenResponse
import com.example.app_api.network.ChatApi
import com.example.app_api.network.GigaChatApiAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ChatRepository(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao,
    private val authApi: GigaChatApiAuth
) {
    private var token: String? = null

    suspend fun getToken(scope: String = "default_scope"): String? {
        return withContext(Dispatchers.IO) {
            val response: Response<TokenResponse> = authApi.getToken(scope).execute()
            if (response.isSuccessful) {
                token = response.body()?.accessToken
                token
            } else {
                null
            }
        }
    }

    fun getCurrentToken(): String? {
        return token
    }

    //сохранение сообщения в базу
    suspend fun saveMessage(message: MessageEntity) {
        messageDao.insertMessage(message)
    }

    //получение всех сообщений для конкретного чата
    fun getMessages(chatId: Long): LiveData<List<MessageEntity>> {
        return messageDao.getMessagesForChat(chatId)
    }

    //для отправки сообщений с сохранением в базу
    suspend fun sendMessage(
        chatId: Long,
        userMessage: String,
        model: String = "gpt-3.5-turbo"
    ): MessageEntity? {
        val userMessageEntity = MessageEntity(
            chatId = chatId,
            sender = "user",
            message = userMessage,
            timestamp = System.currentTimeMillis()
        )
        saveMessage(userMessageEntity) // Сохраняем сообщение пользователя

        val token = getCurrentToken() ?: return null

        val completionRequest = CompletionRequest(
            model = model,
            messages = listOf(
                MessageForRequest("user", userMessage)
            ),
            stream = false,
            updateInterval = 0
        )

        val response = ChatApi.chatApiService.createCompletion(
            "Bearer $token",
            completionRequest
        )

        val gptMessage = response.choices.firstOrNull()?.message?.content ?: return null
        val gptMessageEntity = MessageEntity(
            chatId = chatId,
            sender = "gpt",
            message = gptMessage,
            timestamp = System.currentTimeMillis()
        )
        saveMessage(gptMessageEntity) // Сохраняем ответ GPT-модели

        return gptMessageEntity
    }

}
