package com.cavista.sample.data.datasource

import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.domain.request.SearchItemRequest
import io.reactivex.Observable

interface IRemoteDataTransaction {
    fun getSearchResult(request: SearchItemRequest): Observable<List<UISearchData>>
}