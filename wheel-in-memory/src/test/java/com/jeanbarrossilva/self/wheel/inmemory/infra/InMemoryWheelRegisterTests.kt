package com.jeanbarrossilva.self.wheel.inmemory.infra

import app.cash.turbine.test
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.inmemory.test.inMemory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule

internal class InMemoryWheelRegisterTests {
    @get:Rule
    val wheelRule = WheelTestRule.inMemory()

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN an area WHEN registering it THEN it's been added to the repository`() {
        runTest {
            wheelRule.register.register("Jonathan")
            wheelRule.repository.fetch().test { assertEquals("Jonathan", awaitItem().first().name) }
        }
    }
}
