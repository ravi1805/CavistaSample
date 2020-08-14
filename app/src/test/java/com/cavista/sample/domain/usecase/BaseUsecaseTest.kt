package com.cavista.sample.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cavista.sample.domain.repository.IDataRepo
import com.cavista.sample.domain.thread.IBackgroundThreadExecutor
import com.cavista.sample.domain.thread.IUIThread
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.mockito.Mock
import java.lang.Exception


open class BaseUsecaseTest {
    @get:Rule
    var instantiationExecutoreRule = InstantTaskExecutorRule()

    @Mock
    protected lateinit var mockPostExecutor: IUIThread

    @Mock
    protected lateinit var mockThreadExecutor: IBackgroundThreadExecutor

    @Mock
    protected lateinit var iDataRepo: IDataRepo

    @Mock
    protected lateinit var expectedException: ExpectedException
}
