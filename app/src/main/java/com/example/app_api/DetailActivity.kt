package com.example.app_api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.app_api.databinding.ActivityDetailBinding
import com.example.app_api.models.Result

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        // Получение данных из Intent/
        val character = intent.getParcelableExtra<Result>("character")

        // Заполнение данных/
        character?.let {
            binding.dCharacterName.text = it.name
            binding.dStatus.text = it.status
            binding.dOrigin.text = it.gender
            binding.dSpecies.text = it.species
            Glide.with(this).load(it.image).into(binding.dCharacterImage)
        }
    }
}
