package com.jeanbarrossilva.self.feature.wheel.scope.todo

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.core.utils.get
import com.jeanbarrossilva.self.wheel.inmemory.test.utils.inMemory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ToDoComposerViewModelTests {
    private lateinit var viewModel: ToDoComposerViewModel

    @get:Rule
    val wheelTestRule = WheelTestRule.inMemory()

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = ToDoComposerViewModel(wheelTestRule.editor, FeatureArea.samples)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun composes() {
        val wheelName = ToDoComposerViewModel.WHEEL_NAME
        val area = FeatureArea.sample
        val areaName = area.name
        val toDoTitle = ":D"
        runTest {
            wheelTestRule.register.register(wheelName)
            wheelTestRule.editor.addArea(wheelName, areaName, areaAttention = 0f)
            viewModel.setName(toDoTitle)
            viewModel.compose(area)
            wheelTestRule
                .repository
                .fetch()
                .value[wheelName]
                ?.areas
                ?.get(areaName)
                ?.toDos
                ?.get(toDoTitle)
                .let(::assertNotNull)
        }
    }
}
