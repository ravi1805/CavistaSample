package com.cavista.sample.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cavista.sample.domain.model.CommentDataModel
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.domain.request.CommentReq
import com.cavista.sample.domain.usecase.GetCommentUseCase
import com.cavista.sample.domain.usecase.PostCommentUseCase
import com.cavista.sample.presentation.utils.Resource
import com.cavista.sample.presentation.utils.ResourceState
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
class SearchDetailViewModelTest {
    @get:Rule
    var instantiationExecutoreRule = InstantTaskExecutorRule()

    private lateinit var searchDetailViewModel: SearchDetailViewModel

    private var commentReq: CommentReq = CommentReq("1","msg")

    @Mock
    private lateinit var getCommentUseCase: GetCommentUseCase

    @Mock
    private lateinit var postCommentUseCase: PostCommentUseCase

    @Mock
    private lateinit var resultObserver: Observer<List<CommentDataModel>>

    @Mock
    private lateinit var commentListObserver: TestObserver<List<CommentDataModel>>

    @Mock
    private lateinit var commentListObserable: Observable<List<CommentDataModel>>

    @Mock
    private lateinit var commentList:List<CommentDataModel>

    @Before
    fun setup() {
        searchDetailViewModel = SearchDetailViewModel(getCommentUseCase,postCommentUseCase)
        searchDetailViewModel.commentListLiveData.observeForever(resultObserver)
    }


    @Test
    fun `handle comment data observer`(){
        assertNotNull(searchDetailViewModel.commentListLiveData)
        assertTrue(searchDetailViewModel.commentListLiveData.hasObservers())
    }

    @Test
    fun `fetch comment Result`() {
        Mockito.`when`(getCommentUseCase.build("1")).thenReturn(Observable.just(commentList))
        commentListObserable = getCommentUseCase.build("1")
        commentListObserver = TestObserver()

        commentListObserable.subscribe(commentListObserver)

        commentListObserver.assertComplete()
            .assertNoErrors()
            .assertValue(commentList)

        assertNotNull(searchDetailViewModel.commentListLiveData.postValue(commentList))
        commentListObserver.onNext(commentList)

    }


    @Test
    fun `post comment to local cache`() {
        Mockito.`when`(postCommentUseCase.build(commentReq)).thenReturn(Observable.just(commentList))
        commentListObserable = postCommentUseCase.build(commentReq)
        commentListObserver = TestObserver()

        commentListObserable.subscribe(commentListObserver)

        commentListObserver.assertComplete()
            .assertNoErrors()
            .assertValue(commentList)

        assertNotNull(searchDetailViewModel.commentListLiveData.postValue(commentList))
        commentListObserver.onNext(commentList)

    }


}