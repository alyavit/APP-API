package com.example.app_api.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_api.R
import com.example.app_api.network.ChatApiService

class ChatActivity : AppCompatActivity() {
    private lateinit var chatApiService: ChatApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val chatId = intent.getIntExtra("chatId", 0)

        val sendMessageButton = findViewById<Button>(R.id.sendMessageButton)
        sendMessageButton.setOnClickListener {
            val message = messageEditText.text.toString()
            chatApiService.sendMessage(chatId, message)
        }
    }
}