package com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text.TextFieldDefaults as _TextFieldDefaults

@Composable
internal fun TextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    modifier: Modifier = Modifier,
    validator: TextFieldValidator? = null,
    state: TextFieldState = TextFieldState.Idle,
    onStateChange: (state: TextFieldState) -> Unit,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    label: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    TextField(
        value,
        onValueChange = { changedValue ->
            onValueChange(changedValue)
            validator?.validate(changedValue)?.run(onStateChange)
        },
        state,
        isEnabled,
        isReadOnly,
        label,
        trailingIcon,
        keyboardOptions,
        keyboardActions,
        modifier
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    state: TextFieldState,
    isEnabled: Boolean,
    isReadOnly: Boolean,
    label: (@Composable () -> Unit)?,
    trailingIcon: (@Composable () -> Unit)?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.small)) {
        TextField(
            value,
            onValueChange,
            modifier.fillMaxWidth(),
            isEnabled,
            isReadOnly,
            label = label,
            trailingIcon = trailingIcon?.let {
                {
                    CompositionLocalProvider(
                        LocalContentColor provides _TextFieldDefaults.contentColor,
                        content = it
                    )
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = _TextFieldDefaults.shape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = _TextFieldDefaults.contentColor,
                containerColor = SelfTheme.colors.container.tertiary,
                cursorColor = _TextFieldDefaults.contentColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        AnimatedVisibility(
            visible = state.content != null,
            enter = fadeIn(SelfTheme.animation.spec()) +
                slideInVertically(SelfTheme.animation.spec()) { -it },
            exit = fadeOut(SelfTheme.animation.spec()) +
                slideOutVertically(SelfTheme.animation.spec()) { it }
        ) {
            state.content?.invoke(Modifier.fillMaxWidth())
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun IdleTextFieldPreview() {
    SelfTheme {
        TextField(TextFieldState.Idle)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun InvalidTextFieldPreview() {
    SelfTheme {
        @Suppress("SpellCheckingInspection")
        TextField(TextFieldState.Invalid(message = "Ocorreu um erro."))
    }
}

@Composable
private fun TextField(state: TextFieldState, modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = { },
        state,
        isEnabled = true,
        isReadOnly = false,
        label = {
            @Suppress("SpellCheckingInspection")
            Text("Rótulo")
        },
        trailingIcon = null,
        KeyboardOptions.Default,
        KeyboardActions.Default,
        modifier
    )
}
