package com.jeanbarrossilva.self.feature.questionnaire.core.attention

import androidx.compose.ui.text.input.OffsetMapping

internal class AttentionPercentageOffsetMapping(
    private val percentLength: Int,
    private val percentageLength: Int
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return offset.coerceIn(0, percentageLength)
    }

    override fun transformedToOriginal(offset: Int): Int {
        return offset.coerceIn(0, percentLength)
    }
}
