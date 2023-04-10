package com.jeanbarrossilva.self.feature.questionnaire.step.answerable

import android.content.res.Configuration
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.self.feature.questionnaire.step.Answer
import com.jeanbarrossilva.self.feature.questionnaire.step.Step
import com.jeanbarrossilva.self.feature.questionnaire.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.step.answer.AnswerFieldValue
import com.jeanbarrossilva.self.feature.questionnaire.step.answer.AnswerFieldValueSaver
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun AnswerableStep(
    viewModel: AnswerableStepViewModel,
    position: StepPosition,
    answerFocusRequester: FocusRequester,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    title: @Composable ColumnScope.() -> Unit
) {
    val answer by viewModel.getAnswerFlow().collectAsState()
    val answerFieldValue by rememberSaveable(answer, stateSaver = AnswerFieldValueSaver) {
        mutableStateOf(AnswerFieldValue(answer))
    }

    AnswerableStep(
        position,
        answerFocusRequester,
        answerFieldValue,
        onAnswerFieldValueChange = { viewModel.setAnswer(it.answer) },
        onPrevious,
        onNext,
        modifier,
        title
    )
}

@Composable
internal fun AnswerableStep(
    position: StepPosition,
    answerFocusRequester: FocusRequester,
    answerFieldValue: AnswerFieldValue,
    onAnswerFieldValueChange: (value: AnswerFieldValue) -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    title: @Composable ColumnScope.() -> Unit
) {
    Step(
        position,
        canProceed = answerFieldValue.answer != null,
        title,
        onPrevious,
        onNext,
        modifier
    ) {
        Answer(
            answerFieldValue,
            onAnswerFieldValueChange,
            position == StepPosition.TRAILING,
            onNext,
            Modifier
                .focusRequester(answerFocusRequester)
                .imePadding()
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AnswerableStepPreview() {
    SelfTheme {
        AnswerableStep(
            StepPosition.IN_BETWEEN,
            answerFocusRequester = remember { FocusRequester() },
            AnswerFieldValue.Empty,
            onAnswerFieldValueChange = { },
            onPrevious = { },
            onNext = { }
        ) {
            @Suppress("SpellCheckingInspection")
            Text("Título")
        }
    }
}
