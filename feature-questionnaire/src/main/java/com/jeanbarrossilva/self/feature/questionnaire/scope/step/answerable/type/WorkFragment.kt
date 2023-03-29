package com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type

import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.Swiper
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.AnswerableStepFragment

internal class WorkFragment(
    override val swiper: Swiper,
    override val position: StepPosition,
    private val onDone: () -> Unit
) : AnswerableStepFragment() {
    @Suppress("SpellCheckingInspection")
    override val areaName = "Trabalho"

    @Suppress("SpellCheckingInspection")
    override val titleEnding = "ao seu trabalho"

    override fun onDone() {
        onDone.invoke()
    }
}
