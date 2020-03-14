package com.example.android.bakingapp.bakingList

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

class BakingListViewModel(): ViewModel() {

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
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}
