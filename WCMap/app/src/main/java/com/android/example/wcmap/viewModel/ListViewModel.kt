package com.android.example.wcmap.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.wcmap.model.wc.Records
import com.android.example.wcmap.networkService.WcApi
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    var listWC: MutableLiveData<List<Records>> = MutableLiveData()

    private fun fetchData() {
        viewModelScope.launch {
            try {
                listWC.value = WcApi.retrofitService.getWC().records
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    init {
        fetchData()
    }
}