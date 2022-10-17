package com.example.getyourdrink

import android.app.SearchManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.databinding.FragmentSearchBinding
import com.example.getyourdrink.presentation.adapter.DrinksListAdapter
import com.example.getyourdrink.presentation.viewmodel.DrinkViewModel


class SearchFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: DrinkViewModel
    lateinit var favouritesDrinks: List<Drink>
    private lateinit var sharedPref: SharedPreferences

    private var searchView: SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val menuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        favouritesDrinks = (activity as MainActivity).favouritesDrinks

        sharedPref = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)!!
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val searchableInfo = searchManager.getSearchableInfo(requireActivity().componentName)
        searchView?.setSearchableInfo(searchableInfo)

        searchView?.isIconified = false
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                with (sharedPref.edit()) {
                        putString(getString(R.string.searchQuery), query)
                        apply()
                    }

                if(query != null)
                    getSearched(query)

                searchView?.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

    private fun getSearched(query: String){
        viewModel.getSearched(query).observe(this, Observer {
            if(it != null){
                if(it.isNotEmpty()){
                    val adapter = DrinksListAdapter(it, favouritesDrinks)

                    adapter.setOnItemClickListener {

                        val bundle = Bundle().apply {
                            putString("selected_drink_id", it.idDrink)
                        }

                        try {

                            findNavController().navigate(
                                R.id.action_searchFragment_to_drinkInfoFragment,
                                bundle
                            )
                        }
                        catch (ex: Exception){
                            Log.d("infoError", "${ex.message}")
                        }

                    }

                    binding.rvSearched.layoutManager = LinearLayoutManager(context)
                    binding.rvSearched.adapter = adapter

                    binding.rvSearched.visibility = View.VISIBLE
                    binding.tvNoResults.visibility = View.GONE
                }else{
                    binding.rvSearched.visibility = View.GONE
                    binding.tvNoResults.visibility = View.VISIBLE
                }
            }else{
                Toast.makeText(context, "no search results found", Toast.LENGTH_LONG)
                    .show()
                binding.rvSearched.visibility = View.GONE
                binding.tvNoResults.visibility = View.VISIBLE
            }
        })

        searchView?.setOnCloseListener {
            sharedPref.edit().clear().apply()
            binding.rvSearched.visibility = View.GONE
            binding.tvNoResults.visibility = View.VISIBLE
            true
        }
    }

    override fun onResume() {
        super.onResume()

        val searchQuery = sharedPref.getString(getString(R.string.searchQuery), "")
        if(searchQuery != ""){
            searchQuery?.let { getSearched(it) }
            searchView?.setQuery(searchQuery, false)
        }

    }
}