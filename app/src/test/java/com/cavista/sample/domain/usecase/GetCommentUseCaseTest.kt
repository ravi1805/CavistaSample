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
class GetCommentUseCaseTest : BaseUsecaseTest() {

    private lateinit var getCommentUseCase: GetCommentUseCase

    @Mock
    private lateinit var commentListObserver: TestObserver<List<CommentDataModel>>

    @Mock
    private lateinit var commentListObservable: Observable<List<CommentDataModel>>

    private val searchItemId = "1"

    private val commentDataModel = CommentDataModel("1", "Test Comment", "13/07/2020")

    @Before
    fun setup() {
        getCommentUseCase = GetCommentUseCase(iDataRepo, mockThreadExecutor, mockPostExecutor)
        expectedException = ExpectedException.none()
    }

    @Test
    fun `test data on success call`() {
        `when`(getCommentUseCase.build(searchItemId)).thenReturn(
            Observable.just(
                listOf(
                    commentDataModel
                )
            )
        )
        commentListObservable = getCommentUseCase.build(searchItemId)
        commentListObserver = TestObserver()
        commentListObservable.subscribe(commentListObserver)
        commentListObserver.assertComplete().assertNoErrors().assertValue(listOf(commentDataModel))
    }

    @Test
    fun `test search result on happy case`() {
        getCommentUseCase.build(searchItemId)
        verify(iDataRepo).getCommentList(searchItemId)
        Mockito.verifyNoMoreInteractions(iDataRepo)
        Mockito.verifyZeroInteractions(mockThreadExecutor)
        Mockito.verifyZeroInteractions(mockPostExecutor)
    }

    @Test
    fun `test search result on failur case`() {
        expectedException.expect(NullPointerException::class.java)
        getCommentUseCase.build(searchItemId)
    }
}