package com.example.android.bakingapp.bakingList

import android.util.Log
import androidx.lifecycle.*
import com.example.android.bakingapp.network.BakingApi
import com.example.android.bakingapp.network.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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
            } catch (t: Throwable) {
                _response.value = "Failure: " + t.message
                Log.e("", "The error's from getBakingList")
            }
        }
    }

}
