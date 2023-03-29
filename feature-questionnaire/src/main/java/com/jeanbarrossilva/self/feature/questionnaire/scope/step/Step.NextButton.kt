package com.jeanbarrossilva.self.feature.questionnaire.scope.step

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun NextButton(
    position: StepPosition,
    onClick: () -> Unit,
    isEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(if (isFocused) 8.dp else 6.dp)
    val shape = FloatingActionButtonDefaults.shape
    val containerColor = SelfTheme.colors.container.primary
    val contentColor = SelfTheme.colors.content.primary
    val vector = remember(position) {
        if (position == StepPosition.TRAILING) Icons.Rounded.Done else Icons.Rounded.ArrowForward
    }

    IconButton(
        onClick,
        modifier
            .onFocusChanged { isFocused = it.isFocused }
            .clip(shape)
            .shadow(elevation, shape)
            .size(56.dp),
        isEnabled,
        IconButtonDefaults.filledIconButtonColors(
            containerColor,
            contentColor,
            disabledContainerColor = SelfTheme.colors.container.tertiary,
            disabledContentColor = SelfTheme.colors.content.tertiary
        )
    ) {
        Icon(vector, contentDescription = "Prosseguir")
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EnabledNextButtonPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            NextButton(isEnabled = true)
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DisabledNextButtonPreview() {
    SelfTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            NextButton(isEnabled = false)
        }
    }
}

@Composable
private fun NextButton(isEnabled: Boolean) {
    NextButton(StepPosition.IN_BETWEEN, onClick = { }, isEnabled)
}
