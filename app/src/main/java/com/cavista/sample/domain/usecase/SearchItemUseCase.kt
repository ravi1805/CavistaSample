package com.cavista.sample.domain.usecase

import com.cavista.sample.domain.interactor.BaseUseCase
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.domain.repository.IDataRepo
import com.cavista.sample.domain.request.SearchItemRequest
import com.cavista.sample.domain.thread.IBackgroundThreadExecutor
import com.cavista.sample.domain.thread.IUIThread
import io.reactivex.Observable
import javax.inject.Inject

class SearchItemUseCase
@Inject constructor(
    private val dataRepo: IDataRepo,
    executor: IBackgroundThreadExecutor,
    thread: IUIThread
) : BaseUseCase<SearchItemRequest, List<UISearchData>>(executor, thread) {

    override fun build(request: SearchItemRequest): Observable<List<UISearchData>> {
        return dataRepo.getSearchItemList(request)

    }

}
