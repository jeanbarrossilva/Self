package com.jeanbarrossilva.self.feature.questionnaire.step.answerable.type

import com.jeanbarrossilva.self.feature.questionnaire.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.step.answerable.AnswerableStepFragment

internal class FamilyFragment : AnswerableStepFragment {
    @Suppress("SpellCheckingInspection")
    override val areaName = "Família"

    @Suppress("SpellCheckingInspection")
    override val titleEnding = "à sua família"

    constructor() : super()

    constructor(
        position: StepPosition,
        onPreviousListener: OnPreviousListener,
        onNextListener: OnNextListener
    ) : super(position, onPreviousListener, onNextListener)
}
