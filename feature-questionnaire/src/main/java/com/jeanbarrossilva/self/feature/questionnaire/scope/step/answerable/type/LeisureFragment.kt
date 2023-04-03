package com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type

import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.AnswerableStepFragment

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
