package com.cavista.sample.domain.usecase

import com.cavista.sample.domain.model.CommentDataModel
import com.cavista.sample.domain.request.CommentReq
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
class PostCommentUseCaseTest :BaseUsecaseTest() {

    private lateinit var postCommentUseCase: PostCommentUseCase

    @Mock
    private lateinit var commentListObserver: TestObserver<List<CommentDataModel>>

    @Mock
    private lateinit var commenthListObservable: Observable<List<CommentDataModel>>

    private  var commentReq: CommentReq = CommentReq("1","Test Comment")

    private val commentDataModel = CommentDataModel("1","Test Comment","13/07/2020")

    @Before
    fun setup(){
        postCommentUseCase = PostCommentUseCase(iDataRepo,mockThreadExecutor ,mockPostExecutor)
        expectedException = ExpectedException.none()
    }

    @Test
    fun `test data on success call`(){
        `when`(postCommentUseCase.build(commentReq)).thenReturn(Observable.just(listOf(commentDataModel)))
        commenthListObservable = postCommentUseCase.build(commentReq)
        commentListObserver = TestObserver()
        commenthListObservable.subscribe(commentListObserver)
        commentListObserver.assertComplete().assertNoErrors().assertValue(listOf(commentDataModel))
    }

    @Test
    fun `test search result on happy case`(){
        postCommentUseCase.build(commentReq)
        verify(iDataRepo).postComment(commentReq)
        Mockito.verifyNoMoreInteractions(iDataRepo)
        Mockito.verifyZeroInteractions(mockThreadExecutor)
        Mockito.verifyZeroInteractions(mockPostExecutor)
    }

    @Test
    fun `test search result on failur case`(){
        expectedException.expect(NullPointerException::class.java)
        postCommentUseCase.build(commentReq)
    }
}