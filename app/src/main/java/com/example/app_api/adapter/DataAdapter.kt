package com.example.app_api.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_api.DetailActivity
import com.example.app_api.R
import com.example.app_api.models.Result
import romilp.newsly.Utils.ColorPicker

class DataAdapter (private val context: Context, private val characterList: List<Result>):
    RecyclerView.Adapter<DataAdapter.DataViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val character = characterList[position]
        holder.characterName.text = character.name
        holder.status.text = character.status
        holder.species.text = character.species
        holder.gender.text = character.gender
        Glide.with(context).load(character.image).into(holder.characterImage)
        holder.container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        // Обработка нажатия

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("character", character)
            }
            context.startActivity(intent)
        }
    }
    inner class DataViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val characterImage: ImageView = itemView.findViewById(R.id.characterImage)
        val characterName: TextView = itemView.findViewById(R.id.characterName)
        val status: TextView = itemView.findViewById(R.id.status)
        val species: TextView = itemView.findViewById(R.id.species)
        val location: TextView = itemView.findViewById(R.id.location)
        val gender: TextView = itemView.findViewById(R.id.gender)
        val container = itemView.findViewById<ConstraintLayout>(R.id.cardContainer)
    }
    override fun getItemCount(): Int {
        return characterList.size
    }



}