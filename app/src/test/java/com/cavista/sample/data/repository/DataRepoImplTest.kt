package com.cavista.sample.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.cavista.sample.data.datasource.IRemoteDataTransaction
import com.cavista.sample.data.preferences.UserPreference
import com.cavista.sample.domain.model.CommentDataModel
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.domain.request.SearchItemRequest
import com.cavista.sample.service.INetworkClientService
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DataRepoImplTest {

    @get:Rule
    var instantiationExecutoreRule = InstantTaskExecutorRule()

    private lateinit var dataRepoImpl: DataRepoImpl

    lateinit var userPreference: UserPreference

    @Mock
    private lateinit var iRemoteDataTransaction: IRemoteDataTransaction

    private  var searchItemRequest: SearchItemRequest = SearchItemRequest("Cats",1)
    private val uiSearchItem = UISearchData("1","test 1","")

    @Before
    fun setUp() {
        userPreference = Mockito.mock(UserPreference::class.java)
        dataRepoImpl = DataRepoImpl(iRemoteDataTransaction,userPreference)
    }



    @Test
    fun `get search item when connected to internet`(){
        `when`(iRemoteDataTransaction.getSearchResult(searchItemRequest)).thenReturn(Observable.just(
            listOf(uiSearchItem)))
        val result = dataRepoImpl.getSearchItemList(searchItemRequest)
        assertEquals(1, result.blockingFirst().size)
    }

    @Test
    fun `get search item when not connected to internet`(){
        `when`(iRemoteDataTransaction.getSearchResult(searchItemRequest)).thenReturn(Observable.just(
            listOf()))
        val result = dataRepoImpl.getSearchItemList(searchItemRequest)
        assertEquals(0, result.blockingFirst().size)
    }

}