package com.jeanbarrossilva.self.feature.questionnaire.step

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.self.feature.questionnaire.core.attention.AttentionPercentageVisualTransformation
import com.jeanbarrossilva.self.feature.questionnaire.infra.AttentionConverter
import com.jeanbarrossilva.self.feature.questionnaire.step.answer.AnswerFieldValue
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.wheel.core.domain.Area

internal const val STEP_ANSWER_TAG = "step_answer"

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun Answer(
    value: AnswerFieldValue,
    onValueChange: (value: AnswerFieldValue) -> Unit,
    isTrailing: Boolean,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val answerInPercentAsString =
        remember(value) { value.answer?.let(AttentionConverter::percent)?.toString().orEmpty() }
    val textFieldValue = remember(answerInPercentAsString) {
        TextFieldValue(answerInPercentAsString, value.selection)
    }
    val width = remember { 128.dp }
    val highlightedContentColor = SelfTheme.colors.text.highlighted
    val nonHighlightedContentColor = SelfTheme.colors.text.default
    val textStyle = SelfTheme.text.headline.copy(
        color = highlightedContentColor,
        textAlign = TextAlign.Center
    )

    TextField(
        textFieldValue,
        onValueChange = {
            if (it.text.isNotBlank() && it.text.isDigitsOnly()) {
                val inputAsInt = it.text.toInt()
                val inputInCoreAttention = AttentionConverter.core(inputAsInt)
                onValueChange(AnswerFieldValue(inputInCoreAttention))
            } else {
                onValueChange(AnswerFieldValue(0f))
            }
        },
        modifier
            .width(width)
            .testTag(STEP_ANSWER_TAG),
        textStyle = textStyle,
        placeholder = {
            Text(
                AttentionConverter.percentage(Area.MIN_ATTENTION),
                Modifier.fillMaxWidth(),
                style = textStyle.copy(color = nonHighlightedContentColor)
            )
        },
        visualTransformation = AttentionPercentageVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = if (isTrailing) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (isTrailing) {
                    onNext()
                }
            },
            onNext = {
                if (!isTrailing) {
                    onNext()
                }
            }
        ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = value.answer?.let { highlightedContentColor } ?: Color.Transparent,
            focusedIndicatorColor = highlightedContentColor,
            unfocusedIndicatorColor = nonHighlightedContentColor
        )
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AnswerPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            Answer(AnswerFieldValue.Empty, onValueChange = { }, isTrailing = false, onNext = { })
        }
    }
}
