package com.jeanbarrossilva.self.feature.wheel.scope.todo

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.actionable.button.PrimaryButton
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.dropdown.DropdownField
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text.TextField
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text.TextFieldState
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text.TextFieldValidator
import com.jeanbarrossilva.self.platform.ui.core.sheet.Sheet
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun ToDoComposer(
    viewModel: ToDoComposerViewModel,
    onComposition: () -> Unit,
    modifier: Modifier = Modifier
) {
    val name by viewModel.nameFlow.collectAsState()

    ToDoComposer(
        name,
        onNameChange = viewModel::setName,
        viewModel.areas,
        onComposition = {
            viewModel.compose(it)
            onComposition()
        },
        modifier
    )
}

@Composable
internal fun ToDoComposer(
    name: String,
    onNameChange: (name: String) -> Unit,
    areas: List<FeatureArea>,
    onComposition: (area: FeatureArea) -> Unit,
    modifier: Modifier = Modifier
) {
    val nameFieldFocusRequester = remember(::FocusRequester)
    var nameFieldState by remember { mutableStateOf<TextFieldState>(TextFieldState.Idle) }
    val nameFieldValidator = remember {
        @Suppress("SpellCheckingInspection")
        TextFieldValidator("O nome não pode estar vazio.", String::isNotBlank)
    }
    var isAreasDropdownFieldExpanded by remember { mutableStateOf(false) }
    var selectedArea by remember(areas) { mutableStateOf(areas.first()) }
    val isButtonEnabled = remember(nameFieldState) {
        nameFieldState !is TextFieldState.Invalid
    }

    LaunchedEffect(Unit) {
        nameFieldFocusRequester.requestFocus()
    }

    Sheet(
        title = {
            @Suppress("SpellCheckingInspection")
            Text("Adicionar afazer")
        },
        modifier
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(SelfTheme.sizes.spacing.medium)) {
            TextField(
                name,
                onNameChange,
                Modifier.focusRequester(nameFieldFocusRequester),
                nameFieldValidator,
                nameFieldState,
                onStateChange = { nameFieldState = it },
                label = { Text("Nome") }
            )

            DropdownField(
                isAreasDropdownFieldExpanded,
                onExpansionToggle = { isAreasDropdownFieldExpanded = it },
                text = selectedArea.name,
                label = {
                    @Suppress("SpellCheckingInspection")
                    Text("Área")
                }
            ) { width ->
                areas.forEach { area ->
                    DropdownMenuItem(
                        text = { Text(area.name) },
                        onClick = {
                            selectedArea = area
                            isAreasDropdownFieldExpanded = false
                        },
                        Modifier.width(width),
                        trailingIcon = if (area == selectedArea) {
                            {
                                @Suppress("SpellCheckingInspection")
                                Icon(Icons.Rounded.Check, contentDescription = "Selecionada")
                            }
                        } else {
                            null
                        }
                    )
                }
            }

            PrimaryButton(
                onClick = {
                    nameFieldState = nameFieldValidator.enable(name)
                    if (nameFieldState is TextFieldState.Valid) {
                        nameFieldFocusRequester.freeFocus()
                        onComposition(selectedArea)
                    }
                },
                Modifier.fillMaxWidth(),
                isEnabled = isButtonEnabled
            ) {
                @Suppress("SpellCheckingInspection")
                Text(
                    "Adicionar",
                    color = if (!isButtonEnabled && isSystemInDarkTheme()) {
                        SelfTheme.colors.content.tertiary
                    } else {
                        SelfTheme.colors.content.primary
                    }
                )
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EditingSheetPreview() {
    SelfTheme {
        ToDoComposer(name = "", onNameChange = { }, FeatureArea.samples, onComposition = { })
    }
}
