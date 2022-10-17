package com.example.getyourdrink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.databinding.FragmentDrinkInfoBinding
import com.example.getyourdrink.databinding.FragmentFavouritesBinding
import com.example.getyourdrink.presentation.adapter.IngredientsAdapter
import com.example.getyourdrink.presentation.adapter.IngredientsPhotosAdapter
import com.example.getyourdrink.presentation.viewmodel.DrinkViewModel
import com.google.android.material.snackbar.Snackbar

class DrinkInfoFragment : Fragment() {

    private lateinit var binding: FragmentDrinkInfoBinding
    private lateinit var viewModel: DrinkViewModel
    private lateinit var favourites: List<Drink>
    private var drinkFound = false

    private var mDrink: Drink? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrinkInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        favourites = (activity as MainActivity).favouritesDrinks

        val args: DrinkInfoFragmentArgs by navArgs()
        val drinkId = args.selectedDrinkId
        val drink = args.drink

        if(drink != null){
            mDrink = drink
            drinkFound = favourites.find{fav -> fav.idDrink == mDrink!!.idDrink} != null
            changeButton()
            loadDrink(mDrink!!)
        }
        else{
            getDrink(drinkId!!)
        }


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

    private fun getDrink(id: String){
        val responseLiveData = viewModel.getDrinkById(id)

        activity?.let {
            responseLiveData.observe(it, Observer {
                if (it != null) {
                    mDrink = it
                    drinkFound = favourites.find{fav -> fav.idDrink == mDrink!!.idDrink} != null
                    changeButton()
                    loadDrink(mDrink!!)
                } else {
                    Toast.makeText(requireContext(), "No data available", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }

    private fun loadDrink(drink: Drink){
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