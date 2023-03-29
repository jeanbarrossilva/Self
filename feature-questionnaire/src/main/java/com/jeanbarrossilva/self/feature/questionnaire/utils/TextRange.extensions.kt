package com.jeanbarrossilva.self.feature.questionnaire.utils

import androidx.compose.ui.text.TextRange
import com.jeanbarrossilva.self.feature.questionnaire.domain.attention.Attention
import com.jeanbarrossilva.self.feature.questionnaire.infra.AttentionConverter

internal fun TextRange(@Attention attention: Float?): TextRange {
    val index = attention?.let(AttentionConverter::percent)?.toString().orEmpty().length
    return TextRange(index)
}
