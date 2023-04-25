package com.jeanbarrossilva.self.feature.wheel.scope.todo

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.core.utils.get
import com.jeanbarrossilva.self.wheel.inmemory.test.utils.inMemory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ToDoComposerViewModelTests {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val coroutineDispatcher = StandardTestDispatcher()

    @get:Rule
    val wheelTestRule = WheelTestRule.inMemory()

    private val viewModel = ToDoComposerViewModel(wheelTestRule.editor, FeatureArea.samples)

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun composes() {
        val wheelName = ToDoComposerViewModel.WHEEL_NAME
        val area = FeatureArea.sample
        val areaName = area.name
        val toDoTitle = ":D"
        runTest(coroutineDispatcher) {
            wheelTestRule.register.register(wheelName)
            wheelTestRule.editor.onWheel(wheelName).addArea(areaName, attention = 0f).submit()
            viewModel.setName(toDoTitle)
            viewModel.compose(area)
            wheelTestRule
                .repository
                .fetch()
                .first()[wheelName]
                ?.areas
                ?.get(areaName)
                ?.toDos
                ?.get(toDoTitle)
                .let(::assertNotNull)
        }
    }
}
