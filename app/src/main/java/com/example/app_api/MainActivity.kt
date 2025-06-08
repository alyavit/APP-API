package com.example.app_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.app_api.repository.Response
import com.example.app_api.viewmodels.MainViewModelFactory
import com.example.app_api.viewmodels.MainViewModels
import android.view.Menu
import android.view.MenuInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_api.adapter.DataAdapter
import com.example.app_api.databinding.ActivityMainBinding
import com.example.app_api.models.Result
import com.example.app_api.viewmodels.pageNum


class MainActivity : AppCompatActivity() {
    lateinit var mainViewModels: MainViewModels
    lateinit var adapter: DataAdapter
    lateinit var binding: ActivityMainBinding
    private var result = mutableListOf<Result>()
    //var pageNum = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        //setContentView(R.layout.activity_main)

        val repository = (application as CharacterApplication).characterRepository

        mainViewModels = ViewModelProvider(
            this,
            MainViewModelFactory(repository)
        ).get(MainViewModels::class.java)


        mainViewModels.character.observe(this, {
            when (it) {
                is Response.Loading -> {
                    // Показываем прогресс
                }
                is Response.Success -> {
                    it.data?.let { characterList ->
                        result.addAll(characterList.results) // Добавляем новых персонажей в список
                        adapter.notifyDataSetChanged() // Уведомляем адаптер об изменениях
                    }
                }
                is Response.Error -> {
                    Log.d("TAG", "Error in MAinActivity_observee") // Показываем сообщение об ошибке
                }
            }
        })



        binding.apply {
            characterRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DataAdapter(this@MainActivity, result)
            characterRecyclerView.adapter = adapter


        }


    }



    private fun layoutManager(character: List<Result>) {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.characterRecyclerView.layoutManager = linearLayoutManager

        binding.characterRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)

                // Проверка, если достигнут конец списка
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + pastVisibleItems) >= totalItemCount && dy > 0) {
                    pageNum++ // Увеличиваем номер страницы
                    mainViewModels.getMoreCharacters(pageNum) // Загружаем новую страницу
                }
            }
        })
    }


    private fun getList(character: List<Result>) {
        result.addAll(character)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.delete_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_delete -> {
                mainViewModels.deleteAllData()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}