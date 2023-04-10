package com.jeanbarrossilva.self.feature.questionnaire.step.answer

import androidx.compose.ui.text.TextRange
import com.jeanbarrossilva.self.feature.questionnaire.domain.attention.Attention
import com.jeanbarrossilva.self.feature.questionnaire.infra.AttentionConverter

internal data class AnswerFieldValue(@Attention val answer: Float?) {
    private val text = answer?.let(AttentionConverter::percent)?.toString().orEmpty()

    var selection = TextRange(text.length)
        private set

    constructor(@Attention answer: Float?, selection: TextRange) : this(answer) {
        this.selection = selection
    }

    companion object {
        val Empty = AnswerFieldValue(answer = null)
    }
}
