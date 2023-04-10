package com.jeanbarrossilva.self.feature.questionnaire.step.answerable.type

import com.jeanbarrossilva.self.feature.questionnaire.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.step.answerable.AnswerableStepFragment

internal class StudiesFragment : AnswerableStepFragment {
    @Suppress("SpellCheckingInspection")
    override val areaName = "Estudos"

    @Suppress("SpellCheckingInspection")
    override val titleEnding = "aos estudos"

    constructor() : super()

    constructor(
        position: StepPosition,
        onPreviousListener: OnPreviousListener,
        onNextListener: OnNextListener
    ) : super(position, onPreviousListener, onNextListener)
}
