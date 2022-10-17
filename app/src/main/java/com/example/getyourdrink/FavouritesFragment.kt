package com.example.getyourdrink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.databinding.FragmentFavouritesBinding
import com.example.getyourdrink.presentation.adapter.FavouritesDrinksAdapter
import com.example.getyourdrink.presentation.viewmodel.DrinkViewModel

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var viewModel: DrinkViewModel
    private lateinit var favourites: List<Drink>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        favourites = (activity as MainActivity).favouritesDrinks

        //loadFavouritesDrinks()
        loadTest()
    }

    private fun loadFavouritesDrinks(){
        val responseLiveData = viewModel.getFavourites()

        responseLiveData.observe(requireActivity(), Observer {
            if(it.isNotEmpty()){


                val adapter = FavouritesDrinksAdapter(it)

                adapter.setListener {
                    val bundle = Bundle().apply {
                        putParcelable("drink", it)
                    }

                    findNavController().navigate(
                        R.id.action_favouritesFragment_to_drinkInfoFragment,
                        bundle
                    )
                }

                binding.rvFavourites.layoutManager = GridLayoutManager(context, 2)
                binding.rvFavourites.adapter = adapter

                binding.rvFavourites.visibility = View.VISIBLE
                binding.tvNoDrinks.visibility = View.GONE

            }else{
                binding.rvFavourites.visibility = View.GONE
                binding.tvNoDrinks.visibility = View.VISIBLE
            }
        })
    }

    private fun loadTest(){
        if(favourites.isNotEmpty()){


            val adapter = FavouritesDrinksAdapter(favourites)

            adapter.setListener {
                val bundle = Bundle().apply {
                    putParcelable("drink", it)
                }

                findNavController().navigate(
                    R.id.action_favouritesFragment_to_drinkInfoFragment,
                    bundle
                )
            }

            binding.rvFavourites.layoutManager = GridLayoutManager(context, 2)
            binding.rvFavourites.adapter = adapter

            binding.rvFavourites.visibility = View.VISIBLE
            binding.tvNoDrinks.visibility = View.GONE

        }else{
            binding.rvFavourites.visibility = View.GONE
            binding.tvNoDrinks.visibility = View.VISIBLE
        }
    }

}