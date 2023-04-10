package com.jeanbarrossilva.self.feature.questionnaire.step.answerable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import androidx.fragment.app.viewModels
import com.jeanbarrossilva.self.feature.questionnaire.domain.attention.Attention
import com.jeanbarrossilva.self.feature.questionnaire.step.StepFragment
import com.jeanbarrossilva.self.feature.questionnaire.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.utils.tryToRequestFocus
import com.jeanbarrossilva.self.platform.ui.utils.imeController

internal abstract class AnswerableStepFragment : StepFragment {
    private val viewModel by viewModels<AnswerableStepViewModel>()
    private var onNextListener: OnNextListener? = null
    private val answerFocusRequester = FocusRequester()

    protected abstract val areaName: String
    protected abstract val titleEnding: String

    constructor() : super()

    constructor(
        position: StepPosition,
        onPreviousListener: OnPreviousListener,
        onNextListener: OnNextListener
    ) : super(position, onPreviousListener) {
        this.onNextListener = onNextListener
    }

    fun interface OnNextListener {
        fun onNext(areaName: String, @Attention answer: Float)
    }

    override fun onFocus() {
        answerFocusRequester.tryToRequestFocus()
        imeController?.open()
    }

    @Composable
    override fun Content() {
        AnswerableStep(viewModel, position, answerFocusRequester, ::onPrevious, ::onNext) {
            Title()
        }
    }

    @Composable
    override fun Title() {
        @Suppress("SpellCheckingInspection")
        Text("Numa escala de 0 a 100%, quanto tempo você dedica $titleEnding?")
    }

    private fun onNext() {
        val onNextListener = onNextListener
        val answer = viewModel.getAnswerFlow().value
        if (onNextListener != null && answer != null) {
            onNextListener.onNext(areaName, answer)
        }
    }
}
