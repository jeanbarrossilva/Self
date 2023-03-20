package com.jeanbarrossilva.self.wheel.core.infra

import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.inmemory.test.utils.inMemory
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
                wheelRule.editor.setName(":D", ":P")
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a nonexistent wheel WHEN adding an area to it THEN it throws`() {
        assertFailsWith<AssertionError> {
            runTest {
                wheelRule.editor.addArea(":O", areaName = ":C", areaAttention = 0f)
            }
        }
    }
}
