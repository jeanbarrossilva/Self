package com.jeanbarrossilva.self.feature.questionnaire.step.answerable.type

import com.jeanbarrossilva.self.feature.questionnaire.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.step.answerable.AnswerableStepFragment

internal class WorkFragment : AnswerableStepFragment {
    @Suppress("SpellCheckingInspection")
    override val areaName = "Trabalho"

    @Suppress("SpellCheckingInspection")
    override val titleEnding = "ao seu trabalho"

    constructor() : super()

    constructor(
        position: StepPosition,
        onPreviousListener: OnPreviousListener,
        onNextListener: OnNextListener
    ) : super(position, onPreviousListener, onNextListener)
}
