package com.example.android.bakingapp.bakingList

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.android.bakingapp.network.BakingApi
import com.example.android.bakingapp.network.Ingredient
import com.example.android.bakingapp.network.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class BakingListViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
    get() = _response

    private val _recipe = MutableLiveData<ArrayList<Recipe>>()

    val recipe: LiveData<ArrayList<Recipe>>
    get() = _recipe

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getBakingList()
    }

    private fun getBakingList() {
        coroutineScope.launch {
            val recipeList = BakingApi.retrofitService.getProperties()
            try {
                _recipe.value = recipeList
            } catch (e: Exception) {
                _response.value = "Failure: " + e.message
                Log.e("", "The error's from getBakingList")
            }
        }
    }

    private val _navigateToSelectedRecipe = MutableLiveData<Recipe>()

    val navigateToSelectedRecipe: LiveData<Recipe>
        get() = _navigateToSelectedRecipe

    // initiate navigation to the detail screen on item click
    fun displayRecipeDetail(recipe: Recipe) {
        _navigateToSelectedRecipe.value = recipe
    }

    // set _navigateToSelectedProperty to false once navigation is completed to prevent unwanted extra navigation
    fun displayRecipeDetailComplete() {
        _navigateToSelectedRecipe.value = null
    }
}
