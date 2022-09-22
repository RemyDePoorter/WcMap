package com.android.example.wcmap.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.wcmap.model.wc.Records
import com.android.example.wcmap.networkService.WcApi
import kotlinx.coroutines.launch

class MapsViewModel : ViewModel() {
    var listWC: MutableLiveData<List<Records>> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            try {
                listWC.value = WcApi.retrofitService.getWC().records
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}