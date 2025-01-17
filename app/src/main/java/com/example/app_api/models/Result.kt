package com.example.app_api.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity(tableName = "character")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val gender: String,
    //val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String
) : Parcelable
