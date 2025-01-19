package com.example.app_api

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.app_api.api.CharacterService
import com.example.app_api.api.RetrofitHelper
import com.example.app_api.database.CharacterDatabase
import com.example.app_api.repository.CharacterRepository
import com.example.app_api.worker.CharacterWorkManager
import java.util.concurrent.TimeUnit

class CharacterApplication : Application() {

    lateinit var characterRepository: CharacterRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(CharacterWorkManager::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()

        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val characterService = RetrofitHelper.getInstance().create(CharacterService::class.java)
        val database = CharacterDatabase.getDatabase(applicationContext)
        characterRepository = CharacterRepository(characterService, database, applicationContext)
    }
}