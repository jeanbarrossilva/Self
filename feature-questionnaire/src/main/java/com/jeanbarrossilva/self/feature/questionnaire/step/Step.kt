package com.jeanbarrossilva.self.feature.questionnaire.step

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.Scaffold
import com.jeanbarrossilva.aurelius.utils.`if`
import com.jeanbarrossilva.aurelius.utils.plus
import com.jeanbarrossilva.self.platform.ui.core.ime.ImeState
import com.jeanbarrossilva.self.platform.ui.core.ime.local.LocalImeController
import com.jeanbarrossilva.self.platform.ui.still.Container
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun Step(
    position: StepPosition,
    canProceed: Boolean,
    title: @Composable (ColumnScope.() -> Unit),
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    val spacing = SelfTheme.sizes.spacing.huge
    val titleTextStyle = SelfTheme.text.headline.copy(color = SelfTheme.colors.text.highlighted)
    val isImeOpen = LocalImeController.current.imeState == ImeState.OPEN
    val fabBottomPadding = SelfTheme.sizes.margin.fab.calculateBottomPadding()

    @Suppress("LocalVariableName")
    val _onNext = remember(onNext, canProceed) {
        {
            if (canProceed) {
                onNext()
            }
        }
    }

    Scaffold(
        modifier,
        floatingActionButton = {
            if (position == StepPosition.LEADING) {
                NextButton(position, onClick = _onNext, canProceed, Modifier.imePadding())
            } else {
                Row(
                    Modifier
                        .imePadding()
                        .fillMaxWidth(),
                    Arrangement.SpaceBetween
                ) {
                    PreviousButton(
                        onClick = onPrevious,
                        Modifier.padding(
                            // 16.dp should be the value of AureliusTheme.sizes.margin.fab.end.
                            start = 16.dp * 2
                        )
                    )

                    NextButton(position, onClick = _onNext, canProceed)
                }
            }
        }
    ) {
        LazyColumn(
            Modifier
                .background(SelfTheme.colors.background)
                .fillMaxSize(),
            contentPadding = SelfTheme.sizes.margin.statusBar
                .plus(PaddingValues(spacing))
                .`if`(!isImeOpen) { plus(PaddingValues(bottom = fabBottomPadding - spacing)) },
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(spacing)) {
                    ProvideTextStyle(titleTextStyle) {
                        title()
                    }
                }
            }

            item {
                Box(Modifier.padding(vertical = spacing)) {
                    content()
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LeadingStepWithNextButtonEnabledPreview() {
    SelfTheme {
        Step(StepPosition.LEADING, canProceed = true)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LeadingStepWithNextButtonDisabledPreview() {
    SelfTheme {
        Step(StepPosition.LEADING, canProceed = false)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun InBetweenStepWithNextButtonEnabledPreview() {
    SelfTheme {
        Step(StepPosition.IN_BETWEEN, canProceed = true)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun InBetweenStepWithNextButtonDisabledPreview() {
    SelfTheme {
        Step(StepPosition.IN_BETWEEN, canProceed = false)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TrailingStepWithNextButtonEnabledPreview() {
    SelfTheme {
        Step(StepPosition.TRAILING, canProceed = true)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TrailingStepWithNextButtonDisabledPreview() {
    SelfTheme {
        Step(StepPosition.TRAILING, canProceed = false)
    }
}

@Composable
private fun Step(position: StepPosition, canProceed: Boolean, modifier: Modifier = Modifier) {
    Step(
        position,
        canProceed,
        title = {
            @Suppress("SpellCheckingInspection")
            Text("Título")
        },
        onPrevious = { },
        onNext = { },
        modifier
    ) {
        Container {
            @Suppress("SpellCheckingInspection")
            Text("Conteúdo", style = SelfTheme.text.body)
        }
    }
}
