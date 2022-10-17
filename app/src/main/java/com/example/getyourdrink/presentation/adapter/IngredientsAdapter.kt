package com.example.getyourdrink.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.getyourdrink.data.model.Ingredient
import com.example.getyourdrink.databinding.IngredientItemBinding

class IngredientsAdapter(private val ingredients: List<Ingredient>): RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(private val binding: IngredientItemBinding): RecyclerView.ViewHolder(binding.root){
        val name = binding.tvName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = IngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = ingredients[position]

        holder.name.append(" ${ingredient.name} ${ingredient.measure}")
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}