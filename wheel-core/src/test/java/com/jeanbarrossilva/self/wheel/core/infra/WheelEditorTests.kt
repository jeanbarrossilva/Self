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
    fun `GIVEN a nonexistent wheel WHEN editing it THEN it throws`() {
        assertFailsWith<IllegalArgumentException> {
            runTest {
                wheelRule.editor.onWheel(":D")
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN an existing wheel's nonexistent area WHEN adding a to-do to it THEN it throws`() {
        assertFailsWith<IllegalArgumentException> {
            runTest {
                wheelRule.register.register(":v")
                wheelRule.editor.onWheel(":v").onArea(";)")
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a to-do WHEN adding it to an area THEN it's not yet done`() {
        runTest {
            wheelRule.register.register(":S")
            wheelRule
                .editor
                .onWheel(":S")
                .addArea(name = ":X", attention = .5f)
                .submit()
                .onArea(":X")
                .addToDo("Eat")
                .submit()
            wheelRule.repository.fetch().map { it.first().areas.first().toDos.first() }.test {
                assertFalse(awaitItem().isDone)
            }
        }
    }
}
