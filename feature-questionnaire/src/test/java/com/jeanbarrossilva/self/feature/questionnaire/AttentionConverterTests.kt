package com.jeanbarrossilva.self.feature.questionnaire

import com.jeanbarrossilva.self.feature.questionnaire.infra.AttentionConverter
import org.junit.Assert.assertEquals
import org.junit.Test

internal class AttentionConverterTests {
    @Test
    fun `GIVEN a percent attention WHEN converting it to LTR percentage THEN it's converted`() {
        assertEquals("50%", AttentionConverter.percentage("50"))
    }
}
