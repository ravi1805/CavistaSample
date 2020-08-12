package com.cavista.sample.data.remote

import com.cavista.sample.data.datasource.IRemoteDataTransaction
import com.cavista.sample.service.INetworkClientService
import com.cavista.sample.data.remote.api.IRemoteServiceApi
import com.cavista.sample.domain.request.SearchItemRequest
import com.cavista.sample.data.remote.response.DataApiResponse
import com.cavista.sample.domain.model.UISearchData
import io.reactivex.Observable
import javax.inject.Inject

class RemoteTransactionManager @Inject constructor(
    private val networkService: INetworkClientService
) :
    IRemoteDataTransaction {

    override fun getSearchResult(request: SearchItemRequest): Observable<List<UISearchData>> {
        return Observable.create<DataApiResponse> { emitter ->
            val call = getApiService().getSearchItems(request.pageSeqNumber, request.inputSearchKey)
            val callback = networkService.getJsonCallback(emitter, DataApiResponse::class.java)
            call.enqueue(callback)
        }.flatMap { result ->
            Observable.just(result.mapToDomainModel())
        }
    }

    private fun getApiService(): IRemoteServiceApi {
        return networkService.getNetworkClient(IRemoteServiceApi::class.java)
    }

}

