package com.jeanbarrossilva.self.wheel.core.domain

import com.jeanbarrossilva.self.wheel.core.domain.area.Area
import kotlin.test.Test
import kotlin.test.assertFailsWith

internal class AreaTests {
    @Test
    fun `GIVEN a blank name WHEN creating an area with it THEN it throws`() {
        assertFailsWith<AssertionError> {
            Area(name = " ", attention = .5f)
        }
    }

    @Test
    fun `GIVEN an attention that's less than 0 WHEN creating an area with it THEN it throws`() {
        assertFailsWith<AssertionError> {
            Area(name = "Socializing", attention = -1f)
        }
    }

    @Test
    fun `GIVEN an attention that's greater than 1 WHEN creating an area with it THEN it throws`() {
        assertFailsWith<AssertionError> {
            Area(name = "Work", attention = 1.1f)
        }
    }
}
