package com.example.android.bakingapp.tabs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.bakingapp.network.BakingApi
import com.example.android.bakingapp.network.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class IngredientViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _ingredient = MutableLiveData<ArrayList<Recipe>>()

    val ingredient: LiveData<ArrayList<Recipe>>
        get() = _ingredient

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getIngredients()
    }

    fun getIngredients() {
        coroutineScope.launch {
            val ingredientList = BakingApi.retrofitService.getProperties()
            try {
                _ingredient.value = ingredientList
            } catch (e: Exception) {
                _response.value = "Failure: " + e.message
                Log.e("", "The error's from getBakingList")
            }
        }
    }
}