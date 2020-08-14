package com.cavista.sample.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.domain.request.SearchItemRequest
import com.cavista.sample.domain.usecase.SearchItemUseCase
import com.cavista.sample.presentation.utils.Resource
import com.cavista.sample.presentation.utils.ResourceState
import com.cavista.sample.presentation.utils.setSuccess
import com.cavista.sample.service.INetworkClientService
import getOrAwaitValue
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
    @get:Rule
    var instantiationExecutoreRule = InstantTaskExecutorRule()

    private lateinit var searchViewModel: SearchViewModel

    @Mock
    private lateinit var searchItemUseCase: SearchItemUseCase

    private  var searchItemRequest: SearchItemRequest = SearchItemRequest("Cats",1)

    @Mock
    private lateinit var iNetworkClientService: INetworkClientService

    @Mock
    private lateinit var resultObserver: Observer<Resource<List<UISearchData>>>

    @Mock
    private lateinit var searchListObserver: TestObserver<List<UISearchData>>

    @Mock
    private lateinit var searchListObserable: Observable<List<UISearchData>>

    @Mock
    private lateinit var searchList:List<UISearchData>

    @Before
    fun setup() {
        searchViewModel = SearchViewModel(searchItemUseCase, iNetworkClientService)
        searchViewModel.searchItemLiveData.observeForever(resultObserver)
    }

    @Test
    fun testTagTrue(){
        assertTrue(searchViewModel.TAG == "SearchViewModel")
    }

    @Test
    fun testTagFalse(){
        assertFalse(searchViewModel.TAG == "SearchView")
    }


    @Test
    fun `handle search data observer`(){
        assertNotNull(searchViewModel.searchItemLiveData)
        assertTrue(searchViewModel.searchItemLiveData.hasObservers())
    }

    @Test
    fun `fetch search loading state`() {
        searchViewModel.getSearchItemList(searchItemRequest)
        verify(resultObserver).onChanged(Resource(ResourceState.LOADING))
    }

    @Test
    fun `fetch search item loading state with null data`() {
        searchViewModel.getSearchItemList(searchItemRequest)
        assert(searchViewModel.searchItemLiveData.getOrAwaitValue().state == ResourceState.LOADING)
        assert(searchViewModel.searchItemLiveData.getOrAwaitValue().data?.none() == null)
    }


    @Test
    fun `fetch Search Result State Not Empty`() {

        Mockito.`when`(searchItemUseCase.build(searchItemRequest)).thenReturn(Observable.just(searchList))
        searchListObserable = searchItemUseCase.build(searchItemRequest)
        searchListObserver = TestObserver()

        searchListObserable.subscribe(searchListObserver)

        searchListObserver.assertComplete()
            .assertNoErrors()
            .assertValue(searchList)

        assertNotNull(searchViewModel.searchItemLiveData.setSuccess(searchList))
        searchListObserver.onNext(searchList)

    }


}