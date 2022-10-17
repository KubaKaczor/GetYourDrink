package com.example.getyourdrink.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.data.model.Ingredient
import com.example.getyourdrink.domain.usecase.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DrinkViewModel(
    private val app: Application,
    private val getRandomDrinkUseCase: GetRandomDrinkUseCase,
    private val getDrinkByIdUseCase: GetDrinkByIdUseCase,
    private val getDrinksByCategoryUseCase: GetDrinksByCategoryUseCase,
    private val getSearchedDrinkUseCase: GetSearchedDrinkUseCase,
    private val insertDrinkUseCase: InsertDrinkUseCase,
    private val deleteDrinkUseCase: DeleteDrinkUseCase,
    private val getFavouritesDrinkUseCase: GetFavouritesDrinkUseCase
): AndroidViewModel(app) {

    val drinksList : MutableLiveData<List<Drink>> = MutableLiveData()
    val drink: MutableLiveData<Drink?> = MutableLiveData()

    fun getRandomDrink() = viewModelScope.launch {
        val response = getRandomDrinkUseCase.execute()
        drink.postValue(response)
    }

    fun getDrinksByCategory(category: String) = viewModelScope.launch {
        val response = getDrinksByCategoryUseCase.execute(category)
        drinksList.postValue(response)
    }

    fun getDrinkById(id: String) = liveData {
        val drink = getDrinkByIdUseCase.execute(id)
        emit(drink)
    }

    fun addFavourite(drink: Drink) = viewModelScope.launch {
        insertDrinkUseCase.execute(drink)
    }

    fun deleteFavourite(drink: Drink) = viewModelScope.launch {
        deleteDrinkUseCase.execute(drink)
    }

    fun getFavourites() = liveData {
        val drinks = getFavouritesDrinkUseCase.execute().collect{
            emit(it)
        }
    }

    fun getSearched(query: String) = liveData {
        val searchedDrinks = getSearchedDrinkUseCase.execute(query)
        emit(searchedDrinks)
    }

    fun getIngredients(drink: Drink) : List<Ingredient>{
        val ingredients = ArrayList<Ingredient>()

        //ingredients
        var ingredient1: Ingredient = Ingredient("","")
        var ingredient2: Ingredient = Ingredient("","")
        var ingredient3: Ingredient = Ingredient("","")
        var ingredient4: Ingredient = Ingredient("","")
        var ingredient5: Ingredient = Ingredient("","")


        if(drink.strIngredient1 != null){
            ingredient1.name = drink.strIngredient1
        }
        if(drink.strMeasure1 != null){
            ingredient1.measure = " - ${drink.strMeasure1}"
        }


        if(ingredient1.name != "")
            ingredients.add(ingredient1)



        if(drink.strIngredient2 != null){
            ingredient2.name = drink.strIngredient2
        }
        if(drink.strMeasure2 != null)
            ingredient2.measure = " - ${drink.strMeasure2}"

        if(ingredient2.name != "")
            ingredients.add(ingredient2)



        if(drink.strIngredient3 != null){
            ingredient3.name = drink.strIngredient3
        }
        if(drink.strMeasure3 != null)
            ingredient3.measure = " - ${drink.strMeasure3}"

        if(ingredient3.name != "")
            ingredients.add(ingredient3)



        if(drink.strIngredient4 != null){
            ingredient4.name = drink.strIngredient4
        }
        if(drink.strMeasure4 != null)
            ingredient4.measure = " - ${drink.strMeasure4}"

        if(ingredient4.name != "")
            ingredients.add(ingredient4)



        if(drink.strIngredient5 != null){
            ingredient5.name = drink.strIngredient5.toString()
        }
        if(drink.strMeasure5 != null)
            ingredient5.measure = " - ${drink.strMeasure5}"

        if(ingredient5.name != "")
            ingredients.add(ingredient5)


        return ingredients
    }

}