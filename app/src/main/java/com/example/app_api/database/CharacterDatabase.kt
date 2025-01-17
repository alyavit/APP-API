package com.example.app_api.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.app_api.models.Result


@Database(entities = [Result::class], version = 1)
abstract class CharacterDatabase:RoomDatabase() {
    abstract fun characterDao():CharacterDAO

    companion object{

        val migration_2_3=object :Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE characters" )
            }
        }

        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getDatabase(context: Context): CharacterDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        CharacterDatabase::class.java,
                        "charactersDB")
                        //.addMigrations(migration_2_3)
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}