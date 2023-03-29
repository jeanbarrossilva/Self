package com.jeanbarrossilva.self.feature.questionnaire.core.attention

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.jeanbarrossilva.self.feature.questionnaire.infra.AttentionConverter

internal class AttentionPercentageVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val attentionInPercentageString = AttentionConverter.percentage(text.text)
        val attentionInPercentageAnnotatedString = AnnotatedString(attentionInPercentageString)
        val offsetMapping = AttentionPercentageOffsetMapping(
            percentLength = text.length,
            attentionInPercentageString.length
        )
        return TransformedText(attentionInPercentageAnnotatedString, offsetMapping)
    }
}
