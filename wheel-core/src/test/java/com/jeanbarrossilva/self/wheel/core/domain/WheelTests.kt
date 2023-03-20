package com.jeanbarrossilva.self.wheel.core.domain

import kotlin.test.Test
import kotlin.test.assertFailsWith

internal class WheelTests {
    @Test
    fun `GIVEN a blank name WHEN creating a wheel with it THEN it throws`() {
        assertFailsWith<AssertionError> {
            Wheel(name = " ", areas = emptyList())
        }
    }
}
