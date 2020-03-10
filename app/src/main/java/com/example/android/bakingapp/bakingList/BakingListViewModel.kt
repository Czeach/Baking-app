package com.example.android.bakingapp.bakingList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.bakingapp.network.BakingApi
import com.example.android.bakingapp.network.BakingProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BakingListViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
    get() = _response

    init {
        getBakingListProperties()
    }

    private fun getBakingListProperties() {
        Log.d("json", "testing")

        BakingApi.retrofitService.getProperties().enqueue(object : Callback<List<BakingProperty>> {
            override fun onFailure(call: Call<List<BakingProperty>>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<List<BakingProperty>>, response: Response<List<BakingProperty>>) {
                "Success: ${response.body()?.size} Mars properties retrieved"
                _response.value = "Success: ${response.body()!!.size} Baking properties retrieved"

                Log.d("json", "${call.request().url()}")
            }
        })
    }
}
