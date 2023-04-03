package com.jeanbarrossilva.self.feature.questionnaire.scope.step.unanswerable

import androidx.compose.runtime.Composable
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.Step
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition

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
        Step(
            position,
            canProceed = true,
            title = { Title() },
            ::onPrevious,
            onNext = { onNextListener?.onNext() }
        )
    }
}
