package com.jeanbarrossilva.self.feature.questionnaire.step.answerable.type

import com.jeanbarrossilva.self.feature.questionnaire.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.step.answerable.AnswerableStepFragment

internal class LeisureFragment : AnswerableStepFragment {
    @Suppress("SpellCheckingInspection")
    override val areaName = "Lazer"

    @Suppress("SpellCheckingInspection")
    override val titleEnding = "ao lazer"

    constructor() : super()

    constructor(
        position: StepPosition,
        onPreviousListener: OnPreviousListener,
        onNextListener: OnNextListener
    ) : super(position, onPreviousListener, onNextListener)
}
