package com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type

import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.Swiper
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.AnswerableStepFragment

internal class FamilyFragment(
    override val swiper: Swiper,
    override val position: StepPosition,
    private val onDone: () -> Unit
) : AnswerableStepFragment() {
    @Suppress("SpellCheckingInspection")
    override val areaName = "Família"

    @Suppress("SpellCheckingInspection")
    override val titleEnding = "à sua família"

    override fun onDone() {
        onDone.invoke()
    }
}
