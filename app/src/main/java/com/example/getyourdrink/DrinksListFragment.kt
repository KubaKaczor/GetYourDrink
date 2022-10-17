package com.example.getyourdrink

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.databinding.FragmentDrinksListBinding
import com.example.getyourdrink.presentation.adapter.DrinksListAdapter
import com.example.getyourdrink.presentation.viewmodel.DrinkViewModel

class DrinksListFragment : Fragment() {

    private lateinit var binding: FragmentDrinksListBinding
    private lateinit var viewModel: DrinkViewModel
    private lateinit var favourites: List<Drink>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrinksListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        favourites = (activity as MainActivity).favouritesDrinks

        val args : DrinksListFragmentArgs by navArgs()
        val category = args.selectedCategory

        if(category != null){
            loadDrinks(category)
        }
    }

    private fun loadDrinks(category: String){

        viewModel.getDrinksByCategory(category)

        viewModel.drinksList.observe(requireActivity(), Observer {
            val adapter = DrinksListAdapter(it, favourites)
            adapter.setOnItemClickListener {

                val bundle = Bundle().apply {
                    putString("selected_drink_id", it.idDrink)
                }

                try {

                    findNavController().navigate(
                        R.id.action_drinksListFragment_to_drinkInfoFragment,
                        bundle
                    )
                }
                catch (ex: Exception){
                    Log.d("infoError", "${ex.message}")
                }

            }

            binding.rvDrinks.layoutManager = LinearLayoutManager(context)
            binding.rvDrinks.adapter = adapter
        })
    }
}