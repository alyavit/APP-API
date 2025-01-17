package com.example.app_api.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app_api.models.Result


@Dao
interface CharacterDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(character:List<Result>)

    @Query("SELECT * FROM character Order by id")
    suspend fun getCharacters():List<Result>

    @Query("DELETE FROM character")
    suspend fun deleteAllData()
}
