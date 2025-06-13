package com.example.app_api.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_api.models.CharacterList
import com.example.app_api.repository.CharacterRepository
import com.example.app_api.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

var pageNum = 1

class MainViewModels(private val repository: CharacterRepository) : ViewModel() {

    //private val _characterLiveData = MutableLiveData<Response<CharacterList>>()
    //val character: LiveData<Response<CharacterList>>

    //get() = _characterLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacter(1)
        }
    }


    fun getMoreCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacter(page)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllData()
        }

    }
    val character: LiveData<Response<CharacterList>>
        get() = repository.characters

}