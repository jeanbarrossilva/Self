package com.jeanbarrossilva.self.wheel.core.infra

import app.cash.turbine.test
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.inmemory.test.utils.inMemory
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Rule

internal class WheelEditorTests {
    @get:Rule
    val wheelRule = WheelTestRule.inMemory()

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a nonexistent wheel WHEN editing its name THEN it throws`() {
        assertFailsWith<AssertionError> {
            runTest {
                wheelRule.editor.setName(wheelName = ":D", name = ":P")
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a nonexistent wheel WHEN adding an area to it THEN it throws`() {
        assertFailsWith<AssertionError> {
            runTest {
                wheelRule.editor.addArea(wheelName = ":O", areaName = ":C", areaAttention = 0f)
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a nonexistent wheel WHEN adding a to-do to one of its areas THEN it throws`() {
        assertFailsWith<AssertionError> {
            runTest {
                wheelRule.editor.addToDo(wheelName = ":L", areaName = ":I", toDoTitle = "Do")
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN an existing wheel's nonexistent area WHEN adding a to-do to it THEN it throws`() {
        assertFailsWith<AssertionError> {
            runTest {
                wheelRule.register.register(":v")
                wheelRule.editor.addToDo(wheelName = ":v", areaName = ";)", toDoTitle = ":*")
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a to-do WHEN adding it to an area THEN it's not yet done`() {
        runTest {
            wheelRule.register.register(":S")
            wheelRule.editor.addArea(wheelName = ":S", areaName = ":X", areaAttention = .5f)
            wheelRule.editor.addToDo(wheelName = ":S", areaName = ":X", toDoTitle = "Eat")
            wheelRule.repository.fetch().map { it.first().areas.first().toDos.first() }.test {
                assertFalse(awaitItem().isDone)
            }
        }
    }
}
