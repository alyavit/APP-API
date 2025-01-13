package com.example.app_api.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_api.R
import com.example.app_api.model.Chat
import com.example.app_api.viewmodel.ChatViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/*class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}*/

class MainActivity : AppCompatActivity() {
    private lateinit var chatViewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ChatAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        chatViewModel.allChats.observe(this, Observer<List<Chat>> { chats ->
            chats.let { adapter.setChats(it) }
        })

        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                // Do nothing
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val childView = rv.findChildViewUnder(e.x, e.y)
                if (childView != null) {
                    val position = rv.getChildAdapterPosition(childView)
                    val chatId = adapter.getChatId(position)
                    val intent = Intent(this@MainActivity, ChatActivity::class.java)
                    intent.putExtra("chatId", chatId)
                    startActivity(intent)
                }
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                // Do nothing
            }

            val fab = findViewById<FloatingActionButton>(R.id.fab)
            fab.setOnClickListener {
                val chat = Chat(0, "New Chat", false)
                chatViewModel.insert(chat)
            }
        })
    }
}