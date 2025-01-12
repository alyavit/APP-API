package com.example.app_api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class Chat(
    @PrimaryKey val id: Int,
    val name: String,
    val isFavorite: Boolean
)