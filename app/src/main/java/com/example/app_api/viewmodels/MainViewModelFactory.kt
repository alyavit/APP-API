package com.example.app_api.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_api.repository.CharacterRepository
import com.example.app_api.viewmodels.MainViewModels as MainViewModels1

class MainViewModelFactory(private val repository: CharacterRepository) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModels1(repository) as T
    }
}