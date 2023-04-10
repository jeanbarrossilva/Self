package com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.dropdown

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.jeanbarrossilva.aurelius.utils.toDpSize
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text.TextField
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text.TextFieldDefaults
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text.TextFieldState
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text.TextFieldValidator
import com.jeanbarrossilva.self.feature.wheel.utils.LocalColorScheme
import com.jeanbarrossilva.self.feature.wheel.utils.LocalShapes
import com.jeanbarrossilva.self.feature.wheel.utils.animateDpOffsetAsState
import com.jeanbarrossilva.self.feature.wheel.utils.toDpOffset
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

@Composable
internal fun DropdownField(
    isExpanded: Boolean,
    onExpansionToggle: (isExpanded: Boolean) -> Unit,
    value: String,
    state: TextFieldState,
    onStateChange: (state: TextFieldState) -> Unit,
    label: (@Composable () -> Unit),
    modifier: Modifier = Modifier,
    validator: TextFieldValidator? = null,
    content: @Composable ColumnScope.(width: Dp) -> Unit
) {
    val density = LocalDensity.current
    var top by remember { mutableStateOf(Dp.Unspecified) }
    var size by remember { mutableStateOf(DpSize.Unspecified) }
    val indicatorContentDescription = remember(isExpanded) {
        @Suppress("SpellCheckingInspection")
        if (isExpanded) "Colapsar" else "Expandir"
    }
    val indicatorRotationDegrees by animateFloatAsState(if (isExpanded) -180f else 0f)
    val menuOffset by animateDpOffsetAsState(
        if (top.isSpecified && size.isSpecified) {
            DpOffset(
                x = LocalContext
                    .current
                    .resources
                    ?.configuration
                    ?.screenWidthDp
                    ?.dp
                    ?.div(2)
                    ?.div(size.width / 2.dp)
                    ?: 0.dp,
                y = top + size.height
            )
        } else {
            DpOffset.Zero
        }
    )

    Box {
        TextField(
            value,
            onValueChange = { },
            modifier
                .onPlaced {
                    top = it.positionInRoot().toDpOffset(density).y
                    size = it.size.toDpSize(density)
                }
                .clip(TextFieldDefaults.shape)
                .clickable(role = Role.DropdownList) { onExpansionToggle(true) },
            validator,
            state,
            onStateChange,
            isEnabled = false,
            isReadOnly = true,
            label,
            trailingIcon = {
                Icon(
                    Icons.Rounded.ArrowDropDown,
                    indicatorContentDescription,
                    Modifier.rotate(indicatorRotationDegrees)
                )
            }
        )

        // DropdownMenu hard-codes MaterialTheme.shapes.extraSmall as the shape to be used to clip
        // its content and does not expose a parameter to work around it. 🫠
        CompositionLocalProvider(
            LocalShapes provides MaterialTheme.shapes.copy(extraSmall = SelfTheme.shapes.medium)
        ) {
            DropdownMenu(
                isExpanded,
                onDismissRequest = { onExpansionToggle(false) },
                Modifier.background(TextFieldDefaults.containerColor),
                menuOffset
            ) {
                if (size.width.isSpecified) {
                    // This is more of a convenience for external consumers of the DropdownField
                    // API, since DropdownMenuItem's icons (when in their default state) use
                    // MaterialTheme.colorScheme.onSurfaceVariant to color themselves.
                    CompositionLocalProvider(
                        LocalColorScheme provides MaterialTheme.colorScheme.copy(
                            onSurfaceVariant = TextFieldDefaults.contentColor
                        )
                    ) {
                        content(size.width)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun CollapsedDropdownFieldPreview() {
    SelfTheme {
        DropdownField(isExpanded = false)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ExpandedDropdownFieldPreview() {
    SelfTheme {
        DropdownField(isExpanded = true)
    }
}

@Composable
private fun DropdownField(isExpanded: Boolean, modifier: Modifier = Modifier) {
    @Suppress("SpellCheckingInspection")
    DropdownField(
        isExpanded,
        onExpansionToggle = { },
        value = "Texto",
        TextFieldState.Idle,
        onStateChange = { },
        label = { Text("Rótulo") },
        modifier
    ) { width ->
        0.until(6).forEach { index ->
            DropdownMenuItem(text = { Text("Item $index") }, onClick = { }, Modifier.width(width))
        }
    }
}
