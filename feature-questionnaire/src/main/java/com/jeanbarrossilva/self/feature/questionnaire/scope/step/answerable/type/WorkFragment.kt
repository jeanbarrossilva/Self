package com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type

import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.Swiper
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.AnswerableStepFragment

internal class WorkFragment : AnswerableStepFragment {
    @Suppress("SpellCheckingInspection")
    override val areaName = "Trabalho"

    @Suppress("SpellCheckingInspection")
    override val titleEnding = "ao seu trabalho"

    constructor() : super()

    constructor(swiper: Swiper, position: StepPosition, onDoneListener: OnDoneListener) :
        super(swiper, position, onDoneListener)
}
