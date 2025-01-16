import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_api.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val chatId = intent.getLongExtra("chatId", 0L)
        adapter = MessageAdapter()
        messagesRecyclerView.layoutManager = LinearLayoutManager(this)
        messagesRecyclerView.adapter = adapter

        chatViewModel.getMessages(chatId).observe(this) { messages ->
            adapter.submitList(messages)
        }

        sendMessageButton.setOnClickListener {
            val userMessage = messageEditText.text.toString()
            chatViewModel.sendMessage(chatId, userMessage)
            messageEditText.text.clear()
        }
    }
}
