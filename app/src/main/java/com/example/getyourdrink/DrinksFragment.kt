package com.example.getyourdrink

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.getyourdrink.databinding.FragmentDrawDrinkBinding
import com.example.getyourdrink.databinding.FragmentDrinksBinding
import com.example.getyourdrink.presentation.viewmodel.DrinkViewModel

class DrinksFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentDrinksBinding
    private lateinit var viewModel: DrinkViewModel
    private var searchView: SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding = FragmentDrinksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val listener = object: View.OnClickListener{
            override fun onClick(v: View?) {

                val bundle = Bundle().apply {
                    putString("selected_category", v!!.tag.toString())
                }

                findNavController().navigate(
                    R.id.action_drinksFragment_to_drinksListFragment,
                    bundle
                )
            }
        }

        binding.btnOrdinary.setOnClickListener(listener)
        binding.btnCocktail.setOnClickListener(listener)
        binding.btnShake.setOnClickListener(listener)
        binding.btnCocoa.setOnClickListener(listener)
        binding.btnShot.setOnClickListener(listener)
        binding.btnCoffee.setOnClickListener(listener)
        binding.btnLiqueur.setOnClickListener(listener)
        binding.btnParty.setOnClickListener(listener)
        binding.btnBeer.setOnClickListener(listener)
        binding.btnSoft.setOnClickListener(listener)
        binding.btnOther.setOnClickListener(listener)

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.action_search ->{
                findNavController().navigate(
                    R.id.action_drinksFragment_to_searchFragment
                )
            }
        }
        return true
    }

}