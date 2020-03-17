package com.example.android.bakingapp.ingredientList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.bakingapp.network.BakingApi
import com.example.android.bakingapp.network.Ingredient
import com.example.android.bakingapp.network.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class IngredientListViewModel(recipe: Recipe, app: Application) : AndroidViewModel(app) {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _selectedRecipe = MutableLiveData<ArrayList<Recipe>>()

    val selectedRecipe: LiveData<ArrayList<Recipe>>
    get() = _selectedRecipe

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getIngredientList()
    }

    fun getIngredientList() {
        coroutineScope.launch {
            val ingredientList = BakingApi.retrofitService.getProperties()
            try {
                _selectedRecipe.value = ingredientList
            }catch (e: Exception) {
                _response.value = "Failure: " + e.message
                Log.e("", "The error's from getIngredientList")
            }
        }
    }
}
