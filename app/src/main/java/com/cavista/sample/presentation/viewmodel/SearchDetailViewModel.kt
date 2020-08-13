package com.cavista.sample.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cavista.sample.domain.interactor.DefaultObserver
import com.cavista.sample.domain.model.CommentDataModel
import com.cavista.sample.domain.request.CommentReq
import com.cavista.sample.domain.usecase.GetCommentUseCase
import com.cavista.sample.domain.usecase.PostCommentUseCase
import com.cavista.sample.presentation.utils.Resource
import com.cavista.sample.presentation.utils.setError
import com.cavista.sample.presentation.utils.setLoading
import com.cavista.sample.presentation.utils.setSuccess
import javax.inject.Inject

class SearchDetailViewModel @Inject constructor(
    private val getCommentUseCase: GetCommentUseCase,
    private val postCommentUseCase: PostCommentUseCase
) : ViewModel() {

    val TAG = "SearchViewModel"

    val commentListLiveData = MutableLiveData<List<CommentDataModel>>()

    fun getCommentList(request: String) {
        getCommentUseCase.execute(CommentObserver(),request)
    }

    fun postCommentList(request: CommentReq) {
        postCommentUseCase.execute(CommentObserver(),request)
    }

    private inner class CommentObserver : DefaultObserver<List<CommentDataModel>>() {
        override fun onNext(dataList: List<CommentDataModel>) {
            super.onNext(dataList)
            commentListLiveData.postValue(dataList)
            Log.i(TAG, "Execute onNext")
        }

    }

    override fun onCleared() {
        postCommentUseCase.dispose()
        getCommentUseCase.dispose()
        super.onCleared()
    }

}