package com.example.getyourdrink.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.databinding.FavouriteItemBinding

class FavouritesDrinksAdapter(private var favourites: List<Drink>): RecyclerView.Adapter<FavouritesDrinksAdapter.FavouriteViewHolder>() {

    inner class FavouriteViewHolder(private val binding: FavouriteItemBinding): RecyclerView.ViewHolder(binding.root){
        val image = binding.ivSmallImage
        val name = binding.tvName
        val layout = binding.llFavourite
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = FavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val drink = favourites[position]

        holder.name.text = drink.strDrink

        val posterURL = drink.strDrinkThumb +"/preview"

        Glide.with(holder.image).
        load(posterURL).
        into(holder.image)

        holder.layout.setOnClickListener{
            onClickListener?.let {
                it(drink)
            }
        }
    }

    private var onClickListener: ((Drink) -> Unit)? = null

    fun setListener(listener: ((Drink) -> Unit)){
        onClickListener = listener
    }

    override fun getItemCount(): Int {
        return favourites.size
    }
}