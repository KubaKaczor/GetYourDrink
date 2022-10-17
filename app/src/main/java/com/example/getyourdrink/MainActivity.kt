package com.example.getyourdrink

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.MenuProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.databinding.ActivityMainBinding
import com.example.getyourdrink.presentation.viewmodel.DrinkViewModel
import com.example.getyourdrink.presentation.viewmodel.DrinkViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var factory: DrinkViewModelFactory
    lateinit var viewModel: DrinkViewModel
    lateinit var favouritesDrinks: List<Drink>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,factory)
            .get(DrinkViewModel::class.java)

        viewModel.getFavourites().observe(this, Observer {
            favouritesDrinks = it
        })

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvDrinks.setupWithNavController(
            navController
        )

        viewModel.getRandomDrink()
    }

    override fun onStop() {
        super.onStop()
        val sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)!!

        sharedPref.edit().clear().apply()
    }

}