package com.cavista.sample.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavista.sample.domain.interactor.DefaultObserver
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.domain.request.SearchItemRequest
import com.cavista.sample.domain.usecase.SearchItemUseCase
import com.cavista.sample.presentation.utils.*
import com.cavista.sample.service.INetworkClientService
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchItemUseCase: SearchItemUseCase,
    private val iNetworkClientService: INetworkClientService
) : ViewModel() {

    val TAG = "SearchViewModel"

    val searchItemLiveData = MutableLiveData<Resource<List<UISearchData>>>()
    val errorMsgLiveData = MutableLiveData<String>()

    fun getSearchItemList(searchItemRequest: SearchItemRequest) {
        searchItemLiveData.setLoading()
        if(iNetworkClientService.isMobileNetworkConnected()) {
            if (searchItemRequest.inputSearchKey.isNotEmpty()) {
                Log.i(TAG, "Execute usecase")
                searchItemUseCase.execute(SearchItemObserver(), searchItemRequest)
            } else {
                searchItemLiveData.setSuccess(emptyList())
            }
        }else{
            errorMsgLiveData.postValue(AppUtils.noNetworkMsg)
        }
    }

    private inner class SearchItemObserver : DefaultObserver<List<UISearchData>>() {
        override fun onNext(dataList: List<UISearchData>) {
            super.onNext(dataList)
            searchItemLiveData.setSuccess(dataList)
            Log.i(TAG, "Execute onNext")
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            searchItemLiveData.setError(exception.localizedMessage)
            Log.i(TAG, "Execute onError")
        }

    }

    override fun onCleared() {
        searchItemUseCase.dispose()
        super.onCleared()
    }

}