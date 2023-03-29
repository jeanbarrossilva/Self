package com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable

import androidx.lifecycle.ViewModel
import com.jeanbarrossilva.self.feature.questionnaire.domain.attention.Attention
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class AnswerableStepViewModel : ViewModel() {
    private val answerFlow = MutableStateFlow<@Attention Float?>(0f)

    fun getAnswerFlow(): StateFlow<@Attention Float?> {
        return answerFlow.asStateFlow()
    }

    fun setAnswer(@Attention answer: Float?) {
        answerFlow.value = answer
    }
}
