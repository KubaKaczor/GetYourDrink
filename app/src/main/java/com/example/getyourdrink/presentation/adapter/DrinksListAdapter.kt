package com.example.getyourdrink.presentation.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.getyourdrink.R
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.databinding.DrinkItemBinding

class DrinksListAdapter(private val drinks: List<Drink>, private val favourites: List<Drink>): RecyclerView.Adapter<DrinksListAdapter.DrinkViewHolder>() {

    inner class DrinkViewHolder(binding: DrinkItemBinding): RecyclerView.ViewHolder(binding.root){
        val drinkImage = binding.ivDrinkImage
        val drinkName = binding.tvDrinkName
        val btnFavourite = binding.btnFavourite

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = DrinkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = drinks[position]

        val posterURL = drink.strDrinkThumb +"/preview"

        Glide.with(holder.drinkImage.context).
        load(posterURL).
        into(holder.drinkImage)

        holder.drinkName.text = drink.strDrink

        for(favDrink in favourites){
            if(favDrink.idDrink == drink.idDrink){
                holder.btnFavourite.setColorFilter(ContextCompat.getColor(holder.drinkImage.context, R.color.yellow))
                break
            }
        }



        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(drink)
            }
        }
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    private var onItemClickListener :((Drink)->Unit)?=null

    fun setOnItemClickListener(listener : (Drink)->Unit){
        onItemClickListener = listener
    }
}