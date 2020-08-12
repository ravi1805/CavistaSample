package com.cavista.sample.presentation.viewmodel

import androidx.lifecycle.Observer
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.presentation.utils.Resource
import com.cavista.sample.presentation.utils.ResourceState
import getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify


class SearchViewModelTest{
    @get:Rule
    private lateinit var searchViewModel: SearchViewModel

    @Mock
    lateinit var resultObserver: Observer<Resource<List<UISearchData>>>

    @Before
    fun setup(){
        searchViewModel = SearchViewModel()
    }

    @Test
    fun `fetch search loading state`() {
        searchViewModel.getSearchItemList()
        verify(resultObserver).onChanged(Resource(ResourceState.LOADING))
    }

    @Test
    fun `fetch search item loading state with null data`() {
        searchViewModel.getSearchItemList()
        assert(searchViewModel.searchItemLiveData.getOrAwaitValue().state == ResourceState.LOADING)
        assert(searchViewModel.searchItemLiveData.getOrAwaitValue().data?.none() == null)
    }


    @Test
    fun `fetch Search Result State Not Empty`() {
        searchViewModel.getSearchItemList()
        assert(searchViewModel.searchItemLiveData.getOrAwaitValue().data == listOf(UISearchData("01","","")))

    }

}