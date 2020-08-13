package com.cavista.sample.domain.repository

import com.cavista.sample.domain.model.CommentDataModel
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.domain.request.CommentReq
import com.cavista.sample.domain.request.SearchItemRequest
import io.reactivex.Observable

interface IDataRepo {
    fun getSearchItemList(request: SearchItemRequest): Observable<List<UISearchData>>
    fun getCommentList(itemId: String): Observable<List<CommentDataModel>>
    fun postComment(msg: CommentReq): Observable<List<CommentDataModel>>
}




