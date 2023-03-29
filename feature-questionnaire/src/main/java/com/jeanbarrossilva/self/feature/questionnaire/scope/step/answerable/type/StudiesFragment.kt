package com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type

import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.Swiper
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.AnswerableStepFragment

internal class StudiesFragment(
    override val swiper: Swiper,
    override val position: StepPosition,
    private val onDone: () -> Unit
) : AnswerableStepFragment() {
    @Suppress("SpellCheckingInspection")
    override val areaName = "Estudos"

    @Suppress("SpellCheckingInspection")
    override val titleEnding = "aos estudos"

    override fun onDone() {
        onDone.invoke()
    }
}
