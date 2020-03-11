package com.example.android.bakingapp.bakingList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.bakingapp.network.BakingApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class BakingListViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
    get() = _response

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getBakingListProperties()
    }

    private fun getBakingListProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = BakingApi.retrofitService.getProperties()

            try {
                var listResult = getPropertiesDeferred.await()
                _response.value = "Success ${listResult.size} Baking properties gotten"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
    

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
