package com.cavista.sample.domain.usecase

import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.domain.request.SearchItemRequest
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchItemUseCaseTest :BaseUsecaseTest() {

    private lateinit var searchItemUseCase: SearchItemUseCase

    @Mock
    private lateinit var searchListObserver: TestObserver<List<UISearchData>>

    @Mock
    private lateinit var searchListObservable: Observable<List<UISearchData>>

    private  var searchItemRequest: SearchItemRequest = SearchItemRequest("Cats",1)

    private val uiSearchItem = UISearchData("1","test 1","")

    @Before
    fun setup(){
        searchItemUseCase = SearchItemUseCase(iDataRepo,mockThreadExecutor ,mockPostExecutor)
        expectedException = ExpectedException.none()
    }

    @Test
    fun `test data on success call`(){
        `when`(searchItemUseCase.build(searchItemRequest)).thenReturn(Observable.just(listOf(uiSearchItem)))
        searchListObservable = searchItemUseCase.build(searchItemRequest)
        searchListObserver = TestObserver()
        searchListObservable.subscribe(searchListObserver)
        searchListObserver.assertComplete().assertNoErrors().assertValue(listOf(uiSearchItem))
    }

    @Test
    fun `test search result on happy case`(){
        searchItemUseCase.build(searchItemRequest)
        verify(iDataRepo).getSearchItemList(searchItemRequest)
        Mockito.verifyNoMoreInteractions(iDataRepo)
        Mockito.verifyZeroInteractions(mockThreadExecutor)
        Mockito.verifyZeroInteractions(mockPostExecutor)
    }

    @Test
    fun `test search result on failur case`(){
        expectedException.expect(NullPointerException::class.java)
        searchItemUseCase.build(searchItemRequest)
    }
}