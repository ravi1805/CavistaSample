package com.cavista.sample.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.presentation.utils.Resource
import com.cavista.sample.presentation.utils.setLoading
import com.cavista.sample.presentation.utils.setSuccess
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    val TAG = "SearchViewModel"
    val searchItemLiveData = MutableLiveData<Resource<List<UISearchData>>>()

    fun getSearchItemList() {
        searchItemLiveData.setLoading()
        searchItemLiveData.setSuccess(getTestData())
    }

    override fun onCleared() {
        super.onCleared()
    }


    private fun getTestData(): List<UISearchData> {
        return listOf(
            UISearchData("01", "Title 01", ""),
            UISearchData("01", "Title 01", ""),
            UISearchData("02", "Title 02", ""),
            UISearchData("03", "Title 03", ""),
            UISearchData("04", "Title 04", ""),
            UISearchData("05", "Title 05", "")
        )
    }
}