package com.cavista.sample.data.repository

import com.cavista.sample.domain.repository.IDataRepo
import com.cavista.sample.data.datasource.IRemoteDataTransaction
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.data.preferences.UserPreference
import com.cavista.sample.domain.request.SearchItemRequest
import io.reactivex.Observable
import javax.inject.Inject

class DataRepoImpl @Inject constructor(
    private val remoteDataTransaction: IRemoteDataTransaction,
    private val userPreference: UserPreference
) :
    IDataRepo {
    override fun getSearchItemList(request: SearchItemRequest): Observable<List<UISearchData>> {
        return remoteDataTransaction.getSearchResult(request)
    }

    override fun getCommentList(itemId: String): Observable<List<String>> {
        userPreference.getString(itemId, "")
        return Observable.empty()
    }

}