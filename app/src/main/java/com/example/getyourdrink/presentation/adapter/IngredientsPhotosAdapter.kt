package com.example.getyourdrink.presentation.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.getyourdrink.R
import com.example.getyourdrink.data.model.Ingredient
import com.example.getyourdrink.databinding.IngredientPhotoItemBinding
import java.io.File

class IngredientsPhotosAdapter(private val ingredients: List<Ingredient>): RecyclerView.Adapter<IngredientsPhotosAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(binding: IngredientPhotoItemBinding): RecyclerView.ViewHolder(binding.root){
        val photo = binding.ivPhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = IngredientPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val ingredient = ingredients[position]

        val posterURL = "https://www.thecocktaildb.com/images/ingredients/${ingredient.name}-Small.png"

        val mDefaultBackground = ContextCompat.getDrawable(holder.photo.context, R.drawable.placeholder)

        Glide.with(holder.photo.context).
        load(posterURL).
        error(mDefaultBackground).
        into(holder.photo)

    }

    override fun getItemCount(): Int {
        return  ingredients.size
    }
}