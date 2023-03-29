package com.jeanbarrossilva.self.feature.questionnaire.scope.step.answer

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.ui.text.TextRange

internal object AnswerFieldValueSaver : Saver<AnswerFieldValue, String> {
    override fun restore(value: String): AnswerFieldValue {
        val (answerAsString, selectionStartAsString, selectionEndAsString) = value.split(", ")
        val answer = answerAsString.toFloatOrNull()
        val (selectionStart, selectionEnd) =
            selectionStartAsString.toInt() to selectionEndAsString.toInt()
        val selection = TextRange(selectionStart, selectionEnd)
        return AnswerFieldValue(answer, selection)
    }

    override fun SaverScope.save(value: AnswerFieldValue): String {
        return "${value.answer}, ${value.selection.start}, ${value.selection.end}"
    }
}
