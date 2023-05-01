package com.jeanbarrossilva.self.feature.wheel.test.assertion

import com.jeanbarrossilva.self.feature.wheel.domain.wheel.WheelMetadata
import org.junit.Test

internal class WheelNodeAssertionTests {
    @Test
    fun `GIVEN metadata with the right area names WHEN asserting THEN it doesn't throw`() {
        val metadata = WheelMetadata.empty.copy(x = hashSetOf("A"))
        hasAreas("A") on metadata
    }

    @Test(expected = AssertionError::class)
    fun `GIVEN metadata with missing area names WHEN asserting THEN it throws`() {
        val metadata = WheelMetadata.empty.copy(x = hashSetOf("A", "C"))
        hasAreas("A", "B", "C") on metadata
    }

    @Test
    fun `GIVEN two combined successful assertions WHEN running THEN it doesn't throw`() {
        val metadata = WheelMetadata(x = hashSetOf("A"), y = hashSetOf("50%"))
        hasAreas("A") and hasAttentions("50%") on metadata
    }

    @Test(expected = AssertionError::class)
    fun `GIVEN a combination of successful and failed assertions WHEN running THEN it throws`() {
        val metadata =
            WheelMetadata(x = hashSetOf("B", "C"), y = hashSetOf("80%", "70%"))
        hasAreas("B") and hasAttentions("80%", "30%") on metadata
    }

    @Test(expected = AssertionError::class)
    fun `GIVEN a combination of failed and successful assertions WHEN running THEN it throws`() {
        val metadata = WheelMetadata.empty.copy(y = hashSetOf("80%"))
        hasAttentions("20%") and hasAttentions("80%") on metadata
    }
}
