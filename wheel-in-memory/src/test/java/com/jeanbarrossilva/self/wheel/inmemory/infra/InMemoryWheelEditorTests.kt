package com.jeanbarrossilva.self.wheel.inmemory.infra

import app.cash.turbine.test
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.inmemory.test.utils.inMemory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Rule

internal class InMemoryWheelEditorTests {
    @get:Rule
    val wheelRule = WheelTestRule.inMemory()

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a wheel's name WHEN editing it THEN it's changed in the repository`() {
        runTest {
            wheelRule.register.register("Joan")
            wheelRule.editor.setName("Joan", "Jonas")
            wheelRule.repository.fetch().test { assertEquals("Jonas", awaitItem().first().name) }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN an area WHEN adding it to a wheel THEN it's added in the repository`() {
        runTest {
            wheelRule.register.register("Jean")
            wheelRule.editor.addArea("Jean", areaName = "Work", areaAttention = .8f)
            wheelRule.repository.fetch().map { it.first().areas.first() }.test {
                awaitItem().let { area ->
                    assertEquals("Work", area.name)
                    assertEquals(.8f, area.attention)
                }
            }
        }
    }
}
