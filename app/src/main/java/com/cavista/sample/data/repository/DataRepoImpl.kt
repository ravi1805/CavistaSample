package com.cavista.sample.data.repository

import com.cavista.sample.domain.repository.IDataRepo
import com.cavista.sample.data.datasource.IRemoteDataTransaction
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.data.preferences.UserPreference
import com.cavista.sample.domain.model.CommentDataModel
import com.cavista.sample.domain.request.CommentReq
import com.cavista.sample.domain.request.SearchItemRequest
import com.cavista.sample.presentation.utils.AppUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DataRepoImpl @Inject constructor(
    private val remoteDataTransaction: IRemoteDataTransaction,
    private val userPreference: UserPreference
) :
    IDataRepo {

    override fun getSearchItemList(request: SearchItemRequest): Observable<List<UISearchData>> {
        return remoteDataTransaction.getSearchResult(request)
    }

    override fun getCommentList(id: String): Observable<List<CommentDataModel>> {
        var mutableCommentList = mutableListOf<CommentDataModel>()
        val commentModelListType = object : TypeToken<ArrayList<CommentDataModel>>() {
        }.type
        val jsonString = userPreference.getString(id, "")
        if (!jsonString.isNullOrEmpty()) {
            mutableCommentList = Gson().fromJson(jsonString, commentModelListType)
        }
        return Observable.just(mutableCommentList)
    }

    override fun postComment(req: CommentReq): Observable<List<CommentDataModel>> {
        var mutableCommentList = mutableListOf<CommentDataModel>()
        val commentModelListType = object : TypeToken<ArrayList<CommentDataModel>>() {
        }.type

        val jsonString = userPreference.getString(req.id, "")
        if (!jsonString.isNullOrEmpty()) {
            mutableCommentList = Gson().fromJson(jsonString, commentModelListType)
            mutableCommentList.add(
                CommentDataModel(
                    req.id,
                    req.msg,
                    AppUtils.getCurrentDateNTime()
                )
            )
        } else {
            mutableCommentList.add(
                CommentDataModel(
                    req.id,
                    req.msg,
                    AppUtils.getCurrentDateNTime()
                )
            )
        }
        userPreference.putString(req.id, Gson().toJson(mutableCommentList))
        return Observable.just(mutableCommentList)
    }

}