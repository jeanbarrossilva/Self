package com.jeanbarrossilva.self.wheel.core.infra

import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.inmemory.test.utils.inMemory
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule

internal class WheelRegisterTests {
    @get:Rule
    val wheelRule = WheelTestRule.inMemory()

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a wheel WHEN registering it twice THEN it throws`() {
        assertFailsWith<AssertionError> {
            runTest {
                repeat(2) {
                    wheelRule.register.register(":P")
                }
            }
        }
    }
}
