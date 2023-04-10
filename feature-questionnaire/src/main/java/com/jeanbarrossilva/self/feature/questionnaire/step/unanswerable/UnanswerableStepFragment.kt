package com.jeanbarrossilva.self.feature.questionnaire.step.unanswerable

import androidx.compose.runtime.Composable
import com.jeanbarrossilva.self.feature.questionnaire.step.StepFragment
import com.jeanbarrossilva.self.feature.questionnaire.step.StepPosition

internal abstract class UnanswerableStepFragment : StepFragment {
    private var onNextListener: OnNextListener? = null

    constructor() : super()

    constructor(
        position: StepPosition,
        onPreviousListener: OnPreviousListener,
        onNextListener: OnNextListener
    ) : super(position, onPreviousListener) {
        this.onNextListener = onNextListener
    }

    fun interface OnNextListener {
        fun onNext()
    }

    @Composable
    override fun Content() {
        com.jeanbarrossilva.self.feature.questionnaire.step.Step(
            position,
            canProceed = true,
            title = { Title() },
            ::onPrevious,
            onNext = { onNextListener?.onNext() }
        )
    }
}
