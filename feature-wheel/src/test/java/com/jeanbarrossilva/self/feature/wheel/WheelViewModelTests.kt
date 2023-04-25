package com.jeanbarrossilva.self.feature.wheel

import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.inmemory.test.utils.inMemory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class WheelViewModelTests {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val coroutineDispatcher = StandardTestDispatcher()

    @get:Rule
    val wheelRule = WheelTestRule.inMemory()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val viewModel =
        WheelViewModel(wheelRule.repository, wheelRule.editor, coroutineDispatcher)

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)
    }

    @After
    @OptIn(ExperimentalCoroutinesApi::class)
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun wheelDoesNotExistWhenUnregistered() {
        var isWheelNonexistent: Boolean? = null
        runTest(coroutineDispatcher) {
            viewModel.doOnNonexistentWheel {
                isWheelNonexistent = true
            }
        }
        assertEquals(true, isWheelNonexistent)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun wheelExistsWhenRegistered() {
        var isWheelNonexistent: Boolean? = null
        runTest(coroutineDispatcher) {
            wheelRule.register.register("!")
            viewModel.doOnNonexistentWheel { isWheelNonexistent = true }
        }
        assertNull(isWheelNonexistent)
    }
}
