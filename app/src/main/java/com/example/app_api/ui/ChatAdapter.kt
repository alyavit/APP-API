package com.example.app_api.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_api.R
import com.example.app_api.model.Chat

class ChatAdapter(private var chats: List<Chat>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chats[position]
        holder.textView.text = chat.name
    }

    override fun getItemCount() = chats.size

    fun setChats(chats: List<Chat>) {
        this.chats = chats
        notifyDataSetChanged()
    }

    fun getChatId(position: Int): Int {
        return chats[position].id
    }

}
