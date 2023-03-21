package com.jeanbarrossilva.self.wheel.core.domain

import kotlin.test.Test
import kotlin.test.assertFailsWith

internal class ToDoTests {
    @Test
    fun `GIVEN a blank title WHEN creating a to-do with it THEN it throws`() {
        assertFailsWith<AssertionError> {
            ToDo(title = " ", isDone = false)
        }
    }
}
