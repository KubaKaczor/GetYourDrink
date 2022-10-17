package com.example.getyourdrink

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.databinding.FragmentDrawDrinkBinding
import com.example.getyourdrink.presentation.adapter.IngredientsAdapter
import com.example.getyourdrink.presentation.adapter.IngredientsPhotosAdapter
import com.example.getyourdrink.presentation.viewmodel.DrinkViewModel
import com.google.android.material.snackbar.Snackbar

class DrawDrinkFragment : Fragment() {

    private lateinit var binding: FragmentDrawDrinkBinding
    private lateinit var viewModel: DrinkViewModel
    private lateinit var favourites: List<Drink>

    private var mDrink: Drink? = null
    private var drinkFound = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentDrawDrinkBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        binding.fbRandom.setOnClickListener {
            viewModel.getRandomDrink()
        }

        observeDrink()

        binding.mainContent.btnFavourite.setOnClickListener{
            addOrDeleteFromFavourites(it)
        }
    }



    private fun changeButton(){
        if(drinkFound){
            binding.mainContent.btnFavourite.text = "Usuń z ulubionych"
            binding.mainContent.btnFavourite.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
        }else{
            binding.mainContent.btnFavourite.text = "Dodaj do ulubionych"
            binding.mainContent.btnFavourite.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

    private fun addOrDeleteFromFavourites(view: View){
        if(mDrink != null){
            if(!drinkFound){
                viewModel.addFavourite(mDrink!!)
                Snackbar.make(view, "Dodano do ulubionych", Snackbar.LENGTH_SHORT).show()
                drinkFound = true
                changeButton()
            }else{
                viewModel.deleteFavourite(mDrink!!)
                Snackbar.make(view, "Usunięto z ulubionych", Snackbar.LENGTH_SHORT).show()
                drinkFound = false
                changeButton()
            }
        }
    }

    private fun observeDrink(){

        activity?.let { activity ->
            viewModel.drink.observe(activity, Observer {
                if(it != null) {
                    mDrink = it
                    favourites = (activity as MainActivity).favouritesDrinks
                    drinkFound = favourites.find{fav -> fav.idDrink == mDrink!!.idDrink} != null
                    changeButton()
                    loadView(mDrink!!)
                }
            })
        }
    }

    private fun loadView(drink: Drink){
        binding.mainContent.drinkName.text = drink.strDrink
        binding.mainContent.tvInstructions.text = drink.strInstructions

        if(drink.strAlcoholic == "Alcoholic"){
            binding.mainContent.tvAlcoholFree.visibility = View.GONE
            binding.mainContent.ivNonAcohol.visibility = View.GONE
        }
        else{
            binding.mainContent.tvAlcoholFree.visibility = View.VISIBLE
            binding.mainContent.ivNonAcohol.visibility = View.VISIBLE
        }

        val posterURL = drink.strDrinkThumb +"/preview"
        Glide.with(this).
        load(posterURL).
        into(binding.mainContent.image)

        val ingredients = viewModel.getIngredients(drink)

        //ingredients
        val adapter = IngredientsAdapter(ingredients)


        binding.mainContent.rvIngredients.layoutManager = LinearLayoutManager(requireActivity())
        binding.mainContent.rvIngredients.adapter = adapter

        //ingredients photos
        val adapterPhotos = IngredientsPhotosAdapter(ingredients)

        binding.mainContent.rvIngredientsPhoto.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        binding.mainContent.rvIngredientsPhoto.adapter = adapterPhotos

    }
}