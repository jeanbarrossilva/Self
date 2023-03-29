package com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import androidx.fragment.app.viewModels
import com.jeanbarrossilva.self.feature.questionnaire.QuestionnaireViewModel
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepFragment
import com.jeanbarrossilva.self.feature.questionnaire.utils.tryToRequestFocus
import com.jeanbarrossilva.self.platform.ui.utils.imeController
import org.koin.androidx.viewmodel.ext.android.viewModel

internal abstract class AnswerableStepFragment : StepFragment() {
    private val questionnaireViewModel by viewModel<QuestionnaireViewModel>()
    private val viewModel by viewModels<AnswerableStepViewModel>()
    private val answerFocusRequester = FocusRequester()

    protected abstract val areaName: String
    protected abstract val titleEnding: String

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

    override fun onNext() {
        val answer = viewModel.getAnswerFlow().value
        questionnaireViewModel.answer(areaName, answer)
        super.onNext()
    }
}
