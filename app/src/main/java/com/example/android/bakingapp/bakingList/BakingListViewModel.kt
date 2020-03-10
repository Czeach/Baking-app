package com.example.android.bakingapp.bakingList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BakingListViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
    get() = _response

    private fun getBakingList() {
        _response.value = "Set baking list here"
    }
}
